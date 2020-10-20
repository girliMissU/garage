package com.garage.admin.dao;

import com.garage.admin.model.FaultWarning;
import org.apache.ibatis.annotations.*;


import java.util.List;
@Mapper
public interface FaultWarningDAO {
    String TABLE_NAME = "fault_warning";
    String INSERT_FIELDS = " ji_ting, guang_dian, re_ji_guo_zai, duan_dian, fang_song_lian, ji_xian, gua_gou, xiang_xu ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    FaultWarning selectById(int id);

    @Select({"select * from ", TABLE_NAME})
    List<FaultWarning> selectAll();
}
