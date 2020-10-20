package com.garage.admin.dao;

import com.garage.admin.model.HistorySolution;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistorySolutionDAO {
    String TABLE_NAME = "history_solution";
    String INSERT_FIELDS = " solution_id, garage_id, garage_code, fault_type, fault_content, deal_time. deal_manager, solution ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where garage_id=#{garageId}"})
    HistorySolution selectByGarageId(int garageId);

    @Select({"select * from ", TABLE_NAME})
    List<HistorySolution> selectAll();
}
