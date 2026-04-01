package com.ideage.ams.dao;


import com.ideage.ams.entity.TrxExpenseDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrxExpenseDetailDao {

    List<TrxExpenseDetail> selectAll(@Param("requestId") Integer requestId);

    TrxExpenseDetail selectById(@Param("requestId") Integer requestId);

    int insert(TrxExpenseDetail detail);

    int update(TrxExpenseDetail detail);

    int delete(@Param("id") Integer id);

    Integer selectTotalByRequestId(@Param("requestId") Integer requestId);
}