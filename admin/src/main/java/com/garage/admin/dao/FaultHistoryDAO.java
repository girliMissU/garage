package com.garage.admin.dao;

import com.garage.admin.model.FaultHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FaultHistoryDAO {
    String TABLE_NAME = "fault_history";
    String INSERT_FIELDS = " fault_id, garage_id, garage_code, address, jiechu_state, fault_type, jianxiu_state, submit_time. jiechu_time ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where garage_id=#{garageId}"})
    FaultHistory selectByGarageId(int garageId);

    @Select({"select * from ", TABLE_NAME})
    List<FaultHistory> selectAll();

}
