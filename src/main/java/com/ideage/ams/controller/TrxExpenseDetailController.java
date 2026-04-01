package com.ideage.ams.controller;

import com.ideage.ams.entity.TrxExpenseDetail;
import com.ideage.ams.entity.TrxExpenseRequest;
import com.ideage.ams.service.TrxExpenseDetailService;
import com.ideage.ams.service.TrxExpenseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/expense")
public class TrxExpenseDetailController {
    @Autowired
    private TrxExpenseDetailService expenseDetailService;

    @Autowired
    private TrxExpenseRequestService expenseRequestService;

    @GetMapping("/detail")
    public String showExpenseDetail(@RequestParam("requestId") Integer requestId, Model model, RedirectAttributes redirectAttributes) {

        // 查询报销请求和明细
        List<TrxExpenseDetail> detailList = expenseDetailService.getAll(requestId);
        List<TrxExpenseRequest> requestList = expenseRequestService.getById(requestId);

        if (requestList == null || requestList.isEmpty() || detailList == null || detailList.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "没有数据可显示");
            return "redirect:/IdeageSys";
        }

        TrxExpenseRequest request = requestList.get(0);

        // --- 拼接文件夹绝对路径 ---
        String basePath = "C:/Users/jingh/Downloads/spring-ai-demo/ideage_ams/target/classes/static/";
        String folderPath = basePath + request.getPictureUrl(); // request.getPictureUrl() -> 相对路径 "uploads/1"
        File folder = new File(folderPath);

        List<String> pictures = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    pictures.add(file.getName());
                }
            }
        }

        // 前端访问 URL
        String folderUrl =  request.getPictureUrl() ; // "/uploads/1/"

        // 传给前端
        model.addAttribute("requestId", requestId);
        model.addAttribute("detailList", detailList);
        model.addAttribute("requestList", requestList);
        model.addAttribute("folderUrl", folderUrl);
        model.addAttribute("pictures", pictures);

        return "TrxExpenseRequestDetail :: reimburseDetail";
    }

    @PostMapping("/save")
    public String saveTransport(
            @RequestParam("receiptImage") MultipartFile file,
            @RequestParam("requestId") Integer requestId,
            RedirectAttributes redirectAttributes) {

        if (file == null || file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "请选择要上传的文件");
            return "redirect:/expense/detail?requestId=" + requestId;
        }

        try {
            // 获取静态目录：target/classes/static
            Path staticDir = Paths.get(new ClassPathResource("static").getFile().getAbsolutePath());
            Path uploadsDir = staticDir.resolve("uploads");

            // 根据 requestId 创建子文件夹
            Path requestDir = uploadsDir.resolve(String.valueOf(requestId));
            if (!Files.exists(requestDir)) {
                Files.createDirectories(requestDir);
            }

            // 保存文件
            String fileName = file.getOriginalFilename();
            Path filePath = requestDir.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            redirectAttributes.addFlashAttribute("message", "文件上传成功: " + fileName);
            // 前端展示用 URL
            String fileUrl = "/uploads/" + requestId + "/" + fileName;
            redirectAttributes.addFlashAttribute("fileUrl", fileUrl);

            System.out.println("Saved file path: " + filePath.toAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "文件上传失败: " + e.getMessage());
        }

        String folderUrl = "/uploads/" + requestId;
        expenseRequestService.updateFolder(requestId, folderUrl);

        return "redirect:/expense/detail?requestId=" + requestId;
    }
}
