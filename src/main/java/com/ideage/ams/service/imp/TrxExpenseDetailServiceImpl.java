package com.ideage.ams.service.imp;

import com.ideage.ams.dao.TrxExpenseDetailDao;
import com.ideage.ams.entity.TrxExpenseDetail;
import com.ideage.ams.service.TrxExpenseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("TrxExpenseDetail")
public class TrxExpenseDetailServiceImpl implements TrxExpenseDetailService {
    // 查询全部明细（可按 requestId 查询）
    @Autowired
    private TrxExpenseDetailDao dao;

    @Override
    public List<TrxExpenseDetail> getAll(Integer requestId) {
        return dao.selectAll(requestId);
    }

    @Override
    public TrxExpenseDetail getById(Integer id) {

        return dao.selectById(id);
    }

    @Override
    public int save(TrxExpenseDetail detail) {
        return 0;
    }

    @Override
    public int update(TrxExpenseDetail detail) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public Integer getTotalByRequestId(Integer requestId) {
        return 0;
    }


}
