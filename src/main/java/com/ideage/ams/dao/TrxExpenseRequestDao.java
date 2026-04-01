package com.ideage.ams.dao;

import com.ideage.ams.entity.TrxExpenseRequest;
import com.ideage.ams.utils.PageResult;
import com.ideage.ams.utils.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface TrxExpenseRequestDao {

    List<TrxExpenseRequest> selectById(@Param("requestId") Integer requestId);

    List<TrxExpenseRequest> selectByIf(Map<String, Object> params);

    List<TrxExpenseRequest> selectAll();

    boolean deleteById(@Param("id") Integer requestId);
    
    boolean deleteByIds(@Param("ids") List<Integer> ids);

    int insert(TrxExpenseRequest request);

    int update(TrxExpenseRequest request);


    int getTotalTrxExpenseList(Map<String, Object> map);

    int updateFolder(Integer requestId, String folderUrl);
}