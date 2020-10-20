package com.garage.admin.dao;

import com.garage.admin.model.FaultData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author LIFAN
 * 2019/4/7 21:47
 */
@Mapper
public interface FaultDataDAO {
    String TABLE_NAME = " fault_data ";
    String INSERT_FIELDS = " data ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Update({"update ", TABLE_NAME, " set data = #{data} where id=1"})
    int updateData(@Param("data") String data);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    FaultData selectById(int id);
}
