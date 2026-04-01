package com.ideage.ams.controller;

import com.ideage.ams.common.Constants;
import com.ideage.ams.common.MsgConstants;
import com.ideage.ams.common.Result;
import com.ideage.ams.common.ResultGenerator;
import com.ideage.ams.config.annotation.TokenToUser;
import com.ideage.ams.entity.AdminUser;
import com.ideage.ams.entity.TrxExpenseDetail;
import com.ideage.ams.entity.TrxExpenseRequest;
import com.ideage.ams.service.TrxExpenseDetailService;
import com.ideage.ams.service.TrxExpenseRequestService;
import com.ideage.ams.utils.MassageUtil;
import com.ideage.ams.utils.PageResult;
import com.ideage.ams.utils.PageUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@RestController
@RequestMapping("/expense")
public class TrxExpenseRequestController {

    @Autowired
    private TrxExpenseRequestService expenseRequestService;

      @Autowired
      private TrxExpenseDetailService expenseDetailService;


    /**
     * リストの取得（全部）
     */
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {

        PageUtil pageUtil = new PageUtil(params);

        return ResultGenerator.getSuccessResult(expenseRequestService.getAll(pageUtil));
    }

    /**
     * 詳細情報
     */
    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public Result info(@RequestParam Map<String, Object> params) {

        PageUtil pageUtil = new PageUtil(params);


        if (pageUtil == null || pageUtil.isEmpty()) {
            // 没有结果 → 返回提示信息
            return ResultGenerator.getFailResult("未找到匹配数据，请检查输入内容");
        }

        return ResultGenerator.getSuccessResult(expenseRequestService.getByIf(pageUtil));

    }

    /**
     * 保存
     */
    @PostMapping("/add")
    public Result save(@RequestBody TrxExpenseRequest request, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN, MsgConstants.DEFAULT_MESSAGE_0005);
        }
        if (StringUtils.isEmpty(request.getEmpId())) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR,
                    MassageUtil.format(MsgConstants.DEFAULT_MESSAGE_0007, "社員ID"));
        }
        if (expenseRequestService.create(request) > 0) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("保存失败");
        }
    }

    /**
     * 削除（单条）
     */
    // 单条删除
    @PostMapping("/delete")
    public Result delete(@RequestBody Map<String, Object> params) {

      
        Integer id = (Integer) params.get("id");


        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("page", params.get("page"));
        pageParams.put("limit", params.get("limit"));

        PageUtil pageUtil = new PageUtil(pageParams);

        return ResultGenerator.getSuccessResult(expenseRequestService.deleteById(id,pageUtil));
    }
    
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Map<String, Object> params) {

        List<Integer> ids = (List<Integer>) params.get("ids");

        if (ids == null || ids.isEmpty()) {
            return ResultGenerator.getFailResult("请选择要删除的数据");
        }

        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("page", params.get("page"));
        pageParams.put("limit", params.get("limit"));

        PageUtil pageUtil = new PageUtil(pageParams);
        
        return ResultGenerator.getSuccessResult(expenseRequestService.deleteByIds(ids,pageUtil));
    }


    @GetMapping("/export")
    public void exportExcel(@RequestParam Integer requestId, HttpServletResponse response) throws Exception {

        
        List<TrxExpenseDetail> detailList =  expenseDetailService.getAll(requestId);
        List<TrxExpenseRequest> requestList = expenseRequestService.getById(requestId);

        if (requestList == null || requestList.isEmpty()) {
            throw new RuntimeException("没有报销单数据，无法导出");
        }

        if (detailList == null|| detailList.isEmpty()) {
            throw new RuntimeException("没有报销单数据，无法导出");
        }

        TrxExpenseRequest request = requestList.get(0);
        
        InputStream is = getClass().getClassLoader()
                .getResourceAsStream("templates/template.xlsx");

        Workbook workbook = new XSSFWorkbook(is);
        
        Sheet sheet = workbook.getSheetAt(0);

        workbook.setForceFormulaRecalculation(true);
        //设置基本信息
        Row row1 = sheet.getRow(1);
        row1.getCell(3).setCellValue(detailList.get(0).getSiteName());
        row1.getCell(5).setCellValue(request.getEmpId());
      
        Row row2 = sheet.getRow(2);
        row2.getCell(5).setCellValue(request.getEmpName());
       
      
        
        int rowIndex = 6;
    	
        for (TrxExpenseDetail d : detailList) { 
        	int cellIndex = 1;
            
        	Row row3=sheet.getRow(rowIndex++);

       		row3.getCell(cellIndex++).setCellValue(d.getRequestDate());
       		row3.getCell(cellIndex++).setCellValue(d.getSiteName());
       		row3.getCell(cellIndex++).setCellValue(d.getTransportSection());
       		cellIndex = cellIndex + 2;
       		row3.getCell(cellIndex++).setCellValue(d.getTransportMethod());
       		row3.getCell(cellIndex++).setCellValue(expenseRequestService.getMethodText(d.getTransportExpenseType()));
       		row3.getCell(cellIndex++).setCellValue(d.getTransportAmount()); 
       		row3.getCell(cellIndex++).setCellValue(d.getTransportItem());
       	}
        
        rowIndex = 39;
    	
        for (TrxExpenseDetail d : detailList) { 
        	int cellIndex = 1;
            
        	Row row4=sheet.getRow(rowIndex++);

       		row4.getCell(cellIndex++).setCellValue(d.getRequestDate());
       		row4.getCell(cellIndex++).setCellValue(d.getOtherPayee());
       		row4.getCell(cellIndex++).setCellValue(d.getOtherAccountTitle());
       		row4.getCell(cellIndex++).setCellValue(d.getOtherSummary());
       		cellIndex = cellIndex + 4;
       		row4.getCell(cellIndex++).setCellValue(d.getOtherAmount());
        }
        
       String fileName = URLEncoder.encode("経費精算書_"+request.getEmpName() + ".xlsx",
       		  "UTF-8");
       		  
       response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
       		  response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" +
       		  fileName);
       		  
       		  workbook.write(response.getOutputStream()); 
       		  workbook.close();

        //  关闭资源
         }
    
    @GetMapping("/exportZip")
    public void exportZip(@RequestParam("ids") List<Integer> ids,
                          HttpServletResponse response) throws Exception {
    	
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=expense.zip");

        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());

        for (Integer requestId : ids) {

            List<TrxExpenseDetail> detailList = expenseDetailService.getAll(requestId);
            List<TrxExpenseRequest> requestList = expenseRequestService.getById(requestId);

            if (requestList == null || requestList.isEmpty()) continue;
            if (detailList == null || detailList.isEmpty()) continue;

            TrxExpenseRequest request = requestList.get(0);

            // 👉 每个Excel写到内存
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // ===== 直接复制你原来的Excel代码（唯一改动）=====
            InputStream is = getClass().getClassLoader()
                    .getResourceAsStream("templates/template.xlsx");

            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            workbook.setForceFormulaRecalculation(true);

            Row row1 = sheet.getRow(1);
            row1.getCell(3).setCellValue(detailList.get(0).getSiteName());
            row1.getCell(5).setCellValue(request.getEmpId());

            Row row2 = sheet.getRow(2);
            row2.getCell(5).setCellValue(request.getEmpName());

            int rowIndex = 6;

            for (TrxExpenseDetail d : detailList) {
                int cellIndex = 1;
                Row row3 = sheet.getRow(rowIndex++);

                row3.getCell(cellIndex++).setCellValue(d.getRequestDate());
                row3.getCell(cellIndex++).setCellValue(d.getSiteName());
                row3.getCell(cellIndex++).setCellValue(d.getTransportSection());
                cellIndex += 2;
                row3.getCell(cellIndex++).setCellValue(d.getTransportMethod());
                row3.getCell(cellIndex++).setCellValue(
                        expenseRequestService.getMethodText(d.getTransportExpenseType()));
                row3.getCell(cellIndex++).setCellValue(d.getTransportAmount());
                row3.getCell(cellIndex++).setCellValue(d.getTransportItem());
            }

            rowIndex = 39;

            for (TrxExpenseDetail d : detailList) {
                int cellIndex = 1;
                Row row4 = sheet.getRow(rowIndex++);

                row4.getCell(cellIndex++).setCellValue(d.getRequestDate());
                row4.getCell(cellIndex++).setCellValue(d.getOtherPayee());
                row4.getCell(cellIndex++).setCellValue(d.getOtherAccountTitle());
                row4.getCell(cellIndex++).setCellValue(d.getOtherSummary());
                cellIndex += 4;
                row4.getCell(cellIndex++).setCellValue(d.getOtherAmount());
            }

            workbook.write(baos);
            workbook.close();
            // ===== 结束复制 =====

            // 👉 写入zip
            String fileName = "経費精算書_" + request.getEmpName() + ".xlsx";

            zos.putNextEntry(new ZipEntry(fileName));
            zos.write(baos.toByteArray());
            zos.closeEntry();
        }

        zos.close();
    }
    
    
}
