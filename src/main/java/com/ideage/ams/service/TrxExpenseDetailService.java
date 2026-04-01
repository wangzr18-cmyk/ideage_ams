package com.ideage.ams.service;

import com.ideage.ams.entity.TrxExpenseDetail;
import com.ideage.ams.utils.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrxExpenseDetailService {
    List<TrxExpenseDetail> getAll(Integer requestId);

    TrxExpenseDetail getById(Integer requestId);

    int save(TrxExpenseDetail detail);

    int update(TrxExpenseDetail detail);

    int delete(Integer id);

    Integer getTotalByRequestId(Integer requestId);


}
