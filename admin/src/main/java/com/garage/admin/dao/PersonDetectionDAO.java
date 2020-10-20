package com.garage.admin.dao;

import com.garage.admin.model.PersonDetection;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;


/**
 * @Author: LiFan
 * @Date: 2020/6/17
 */
@Mapper
public interface PersonDetectionDAO {
    String TABLE_NAME = " person_detection ";
    String INSERT_FIELDS = " garage_id, if_person, send_time ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Update({"update ", TABLE_NAME, " set garage_id=#{garageId},if_person=#{ifPerson},send_time=#{sendTime} where id=#{id}"})
    void updateDetection(PersonDetection personDetection);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=1"})
    PersonDetection selectDetection();

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id>1"})
    List<PersonDetection> selectHistroy();
}
