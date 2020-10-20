package com.garage.admin.dao;

import com.garage.admin.model.MotorRunningData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @Author: LiFan
 * @Date: 2019/11/3
 */
@Mapper
public interface MotorRunningDataDAO {
    String TABLE_NAME = "motor_running_data";
    String INSERT_FIELDS = " run_time, i, n, i_true, n_true ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " order by run_time desc limit 20"})
    List<MotorRunningData> selectRecently();

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where run_time >= #{startTime} and run_time <= #{endTime}"})
    List<MotorRunningData> selectRange(Date startTime, Date endTime);
}
