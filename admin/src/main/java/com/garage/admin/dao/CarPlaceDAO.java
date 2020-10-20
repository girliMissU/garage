package com.garage.admin.dao;

import com.garage.admin.model.CarPlace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarPlaceDAO {
    String TABLE_NAME = "no1_car_place";
    String INSERT_FIELDS = " cp_num, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;


    @Select({"select * from ", TABLE_NAME})
    List<CarPlace> selectAll();

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where status=#{status}"})
    List<CarPlace> selectByStatus(int Status);
}
