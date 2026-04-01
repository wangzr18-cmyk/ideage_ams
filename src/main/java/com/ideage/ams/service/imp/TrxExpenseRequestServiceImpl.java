package com.ideage.ams.service.imp;

import com.ideage.ams.dao.TrxExpenseRequestDao;
import com.ideage.ams.entity.TrxExpenseDetail;
import com.ideage.ams.entity.TrxExpenseRequest;
import com.ideage.ams.service.TrxExpenseRequestService;
import com.ideage.ams.utils.PageResult;
import com.ideage.ams.utils.PageUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("trxExpenseRequestService")
public class TrxExpenseRequestServiceImpl implements TrxExpenseRequestService {

    @Autowired
    private TrxExpenseRequestDao mapper;

    @Override
    public PageResult getByIf(PageUtil pageUtil) {
        List<TrxExpenseRequest> TrxList = mapper.selectByIf(pageUtil);

        //  PageResult pageResult = new PageResult(TrxList, total, pageUtil.getLimit(), pageUtil.getPage());
//写法需要改
        int total = mapper.getTotalTrxExpenseList(pageUtil);
        int pageNum = pageUtil.getPage();
        int pageSize = pageUtil.getLimit();
        int fromIndex = (pageNum - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, total);

        List<TrxExpenseRequest> pageList = TrxList.subList(fromIndex, toIndex);

        PageResult pageResult = new PageResult(pageList, total, pageSize,pageNum);

        return pageResult;
    }

    @Override
    public PageResult getAll(PageUtil pageUtil) {

        List<TrxExpenseRequest> TrxList = mapper.selectAll();

        int total = mapper.getTotalTrxExpenseList(pageUtil);

      //  PageResult pageResult = new PageResult(TrxList, total, pageUtil.getLimit(), pageUtil.getPage());
//写法需要改

        int fromIndex = (pageUtil.getPage() - 1) * pageUtil.getLimit();
        int toIndex = Math.min(fromIndex + pageUtil.getLimit(), total);

        List<TrxExpenseRequest> pageList = TrxList.subList(fromIndex, toIndex);

        PageResult pageResult = new PageResult(pageList, total, pageUtil.getLimit(),pageUtil.getPage());

        return pageResult;
    }

    @Override
    public PageResult  deleteById(Integer id,PageUtil pageUtil) {

        boolean ok = mapper.deleteById(id);
        List<TrxExpenseRequest> TrxList = mapper.selectAll();

        int total = mapper.getTotalTrxExpenseList(pageUtil);

        int fromIndex = (pageUtil.getPage() - 1) * pageUtil.getLimit();
        int toIndex = Math.min(fromIndex + pageUtil.getLimit(), total);

        List<TrxExpenseRequest> pageList = TrxList.subList(fromIndex, toIndex);

        PageResult pageResult = new PageResult(pageList, total, pageUtil.getLimit(),pageUtil.getPage());

        return pageResult;
    }
    
    public PageResult deleteByIds(List<Integer> ids,PageUtil pageUtil) {
    	
    	 boolean ok = mapper.deleteByIds(ids);
    	
    	 List<TrxExpenseRequest> TrxList = mapper.selectAll();

         int total = mapper.getTotalTrxExpenseList(pageUtil);

         int fromIndex = (pageUtil.getPage() - 1) * pageUtil.getLimit();
         int toIndex = Math.min(fromIndex + pageUtil.getLimit(), total);

         List<TrxExpenseRequest> pageList = TrxList.subList(fromIndex, toIndex);

         PageResult pageResult = new PageResult(pageList, total, pageUtil.getLimit(),pageUtil.getPage());
    	
    	 return pageResult;
    }

    @Override
    public List<TrxExpenseRequest> getById(Integer requestId) {
        return mapper.selectById(requestId);
    }

    @Override
    public int create(TrxExpenseRequest request) {
        return mapper.insert(request);
    }

    @Override
    public int update(TrxExpenseRequest request) {
        return mapper.update(request);
    }


    @Override
    public int updateFolder(Integer requestId, String folderUrl){
         return mapper.updateFolder(requestId,folderUrl);
    }

    @Override
    public String getMethodText(String method) {
    	 switch (method) {
         case "1": return "片道";
         case "2": return "往復";
         case "3": return "定期";
         default:return "";
        }

    }
}