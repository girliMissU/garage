package com.garage.admin.dao;

import com.garage.admin.model.Solution;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SolutionDAO {
    String TABLE_NAME = " history_fault_solutions ";
    String INSERT_FIELDS = " fault_code, deal_time, deal_manager, solution ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{faultCode},#{dealTime},#{dealManager},#{solution})"})
    int addSolution(Solution Solution);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Solution getById(int id);


}
