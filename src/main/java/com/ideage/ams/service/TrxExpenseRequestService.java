package com.ideage.ams.service;

import com.ideage.ams.entity.TrxExpenseDetail;
import com.ideage.ams.entity.TrxExpenseRequest;
import com.ideage.ams.utils.PageResult;
import com.ideage.ams.utils.PageUtil;
import com.ideage.ams.entity.AttendanceList;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface TrxExpenseRequestService {

    public PageResult getByIf(PageUtil pageUtil);

    public PageResult getAll(PageUtil pageUtil);

    public PageResult deleteById(Integer id,PageUtil pageUtil);
    
    public PageResult deleteByIds(List<Integer> ids,PageUtil pageUtil);

    List<TrxExpenseRequest> getById(Integer requestId);

    int create(TrxExpenseRequest request);

    int update(TrxExpenseRequest request);

    int updateFolder(Integer requestId, String folderUrl);
    
    String getMethodText(String method);
    
    

}