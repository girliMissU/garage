package com.garage.admin.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.garage.admin.model.Maintenance;
import org.springframework.stereotype.Repository;

@Mapper
public interface MaintenanceDao {
    String TABLE_NAME = " maintenance ";
    String INSERT_FIELDS = " garage_id, content, start_date, end_date, operator, status, manager, address, maintaincompany, log ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;
    
	List<Maintenance> selectLatestRecord(
			@Param("username") String username,
			@Param("offset") int offset,
            @Param("limit") int limit
            );
	
	List<Maintenance> selectLatestRecordByStatus(@Param("username") String username,
			@Param("offset") int offset,
            @Param("limit") int limit);
	
	@Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
		") values (#{garageId},#{content},#{startDate},#{endDate},#{operator},#{status},#{manager},#{address},#{maintainCompany},#{log})"})
	int addMaintenance(Maintenance mt);

	@Delete({"delete from ", TABLE_NAME, " where id = #{id}"})
	void deleteById(Integer id);

	List<Maintenance> selectByCondition(Maintenance maint);

	@Select("select"+SELECT_FIELDS+"from"+TABLE_NAME+"where id = #{id}")
	Maintenance selectById(Integer id);

	@Update("update"+TABLE_NAME+"set end_date=#{endDate,jdbcType=TIMESTAMP},status='已完成',log=#{log} where id=#{id}")
	void updateById(@Param("id")Integer id, @Param("endDate")Date parse, @Param("log")String log);

}
