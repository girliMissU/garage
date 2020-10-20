package com.garage.admin.dao;

import com.garage.admin.model.ScreenStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author LIFAN
 * 2018/12/5 12:29
 */
@Mapper
public interface ScreenStatusDAO {
    String TABLE_NAME = " screen_status ";
    String INSERT_FIELDS = " status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Update({"update ", TABLE_NAME, " set status = #{status} where id=1"})
    int updateStatus(@Param("status") int status);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    ScreenStatus selectById(int id);
}
