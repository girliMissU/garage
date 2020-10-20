package com.garage.admin.dao;

import com.garage.admin.model.FaultTrigger;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author LIFAN
 * 2019/3/11 11:46
 */
@Mapper
public interface FaultTriggerDAO {
    String TABLE_NAME = "fault_trigger";
    String INSERT_FIELDS = " garage_id, garage_code, fault_name, data_point, trigger_condition, alarm_way, contact_id, trigger_status, update_time ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    FaultTrigger selectById(int id);

    @Select({"select * from ", TABLE_NAME})
    List<FaultTrigger> selectAll();

    @Update({"update ", TABLE_NAME, " set garage_id=#{garageId},fault_name=#{faultName},data_point=#{dataPoint},trigger_condition=#{triggerCondition}," +
            "alarm_way=#{alarmWay},contact_id=#{contactId},trigger_status=#{triggerStatus},update_time=#{updateTime} where id=#{triggerId}"})
    int updateTrigger(@Param("triggerId") int triggerId,
                      @Param("garageId") int garageId,
                      @Param("faultName") String faultName,
                      @Param("dataPoint") String dataPoint,
                      @Param("triggerCondition") String triggerCondition,
                      @Param("alarmWay") String alarmWay,
                      @Param("contactId") int contactId,
                      @Param("triggerStatus") int triggerStatus,
                      @Param("updateTime") Date updateTime);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{garageId},#{garageCode},#{faultName},#{dataPoint},#{triggerCondition},#{alarmWay},#{contactId},#{triggerStatus},#{updateTime})"})
    int addTrigger(FaultTrigger faultTrigger);

    @Delete({"delete from ", TABLE_NAME, " where id=#{triggerId}"})
    void deleteById(int triggerId);

}
