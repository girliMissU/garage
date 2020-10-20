package com.garage.admin.dao;

import com.garage.admin.model.Problem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProblemDAO {
    String TABLE_NAME = " problem ";
    String INSERT_FIELDS = " garage_id, title, content, created_date, username, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{garageId},#{title},#{content},#{createdDate},#{username},#{status})"})
    int addProblem(Problem problem);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where username=#{username}"})
    List<Problem> getByUsername(String username);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Problem getById(int id);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);

    List<Problem> selectLatestProblems(@Param("username") String username, @Param("offset") int offset,
                                        @Param("limit") int limit);
}
