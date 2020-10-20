package com.garage.admin.dao;

import com.garage.admin.model.LifeManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LifeManageDAO {
    String TABLE_NAME = "life_manage";
    String INSERT_FIELDS = " garage_id, garage_code, address, start_time, end_time ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where garage_id=#{garageId}"})
    LifeManage selectByGarageId(int garageId);

    @Select({"select * from ", TABLE_NAME})
    List<LifeManage> selectAll();
}
