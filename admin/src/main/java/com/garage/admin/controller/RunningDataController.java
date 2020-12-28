package com.garage.admin.controller;


import com.garage.admin.dao.MotorRunningDataDAO;
import com.garage.admin.dao.RunningDataDAO;
import com.garage.admin.model.MotorRunningData;
import com.garage.admin.model.RunningData;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class RunningDataController {

    @Autowired
    private RunningDataDAO runningDataDAO;

    @Autowired
    private MotorRunningDataDAO motorRunningDataDAO;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //车库运行情况总览
    @RequestMapping(value = "/all_running_data",method = {RequestMethod.POST})
    public List<RunningData> getAllRunningData(@Param("authority") int authority) {
        List<RunningData> list = new ArrayList<RunningData>();
        if(authority == 0) {
            //最高管理员返回所有车库运行情况
            list = runningDataDAO.selectAll();
        }else{
            list.add(runningDataDAO.selectById(authority));
        }
        return list;
    }

    //最近的电机电流与转速运行数据
    @RequestMapping(value = "/motor_running_data",method = {RequestMethod.POST})
    public List<MotorRunningData> getAllRunningData() {
        return motorRunningDataDAO.selectRecently();
    }

    @GetMapping(value = "/get_motor_data")
    public List<MotorRunningData> getRunningDataByDate(@RequestParam("start_time") String startTime,
                                                       @RequestParam("end_time") String endTime){
        try {
            if (startTime==null||startTime.length()==0||endTime==null||endTime.length()==0){
                return motorRunningDataDAO.selectRecently();
            }
            return motorRunningDataDAO.selectRange(stringToDate(startTime),stringToDate(endTime));
        } catch (ParseException e) {
            log.error("日期转换错误"+e);
            e.printStackTrace();
        }
        return null;
    }
    private Date stringToDate(String s) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(s);
    }
}
