package com.garage.admin.dao;

import com.garage.admin.model.FaultContact;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author LIFAN
 * 2019/3/11 12:19
 */
@Mapper
public interface FaultContactDAO {
    String TABLE_NAME = "fault_contact";
    String INSERT_FIELDS = " username, phone_num, email, exts ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    FaultContact selectById(int id);

    @Select({"select * from ", TABLE_NAME})
    List<FaultContact> selectAll();

    @Update({"update ", TABLE_NAME, " set username=#{username},phone_num=#{phoneNum},email=#{email}," +
            "exts=#{exts} where id=#{contactId}"})
    int updateContact(@Param("contactId") int contactId,
                      @Param("username") String username,
                      @Param("phoneNum") String phoneNum,
                      @Param("email") String email,
                      @Param("exts") String exts);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{username},#{phoneNum},#{email},#{exts})"})
    int addContact(FaultContact faultContact);

    @Delete({"delete from ", TABLE_NAME, " where id=#{contactId}"})
    void deleteById(int contactId);
}
