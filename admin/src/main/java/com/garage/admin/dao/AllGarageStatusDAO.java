package com.garage.admin.dao;

import com.garage.admin.model.AllGarageStatus;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AllGarageStatusDAO {
    String TABLE_NAME = "all_garage_status";
    String INSERT_FIELDS = " total_num, used_num, not_used_num, fault_num ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    AllGarageStatus selectById(int id);

}
