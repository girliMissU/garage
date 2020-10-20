package com.garage.admin.dao;

import com.garage.admin.model.RunningData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RunningDataDAO {
    String TABLE_NAME = "running_data";
    String INSERT_FIELDS = " status, left_w, left_n, left_i, right_w, right_n, right_i, horizontal_w, horizontal_n, horizontal_i ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    RunningData selectById(int id);

    @Select({"select * from ", TABLE_NAME})
    List<RunningData> selectAll();

}
