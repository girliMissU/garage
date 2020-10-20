package com.garage.admin.dao;

import com.garage.admin.model.Manager;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ManagerDAO {
    String TABLE_NAME = " garage_manager ";
    String INSERT_FIELDS = " username, password, salt, authority";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{username},#{password},#{salt},#{authority})"})
    int addUser(Manager manager);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Manager selectById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where username=#{username}"})
    Manager selectByUserName(String username);

    @Select({"select * from ", TABLE_NAME})
    List<Manager> selectAll();

    @Update({"update ", TABLE_NAME, " set password=#{password} where id=#{id}"})
    void updatePassword(Manager manager);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);

}
