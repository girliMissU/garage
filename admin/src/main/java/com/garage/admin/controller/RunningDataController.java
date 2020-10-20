package com.garage.admin.controller;


import com.garage.admin.dao.MotorRunningDataDAO;
import com.garage.admin.dao.RunningDataDAO;
import com.garage.admin.model.MotorRunningData;
import com.garage.admin.model.RunningData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class RunningDataController {

    @Autowired
    private RunningDataDAO runningDataDAO;

    @Autowired
    private MotorRunningDataDAO motorRunningDataDAO;


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
}
