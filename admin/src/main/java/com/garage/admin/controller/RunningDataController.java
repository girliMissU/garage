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
import java.util.Collections;
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

    /**
     * 最近的电机电流与转速运行数据，没补零
     */
    @RequestMapping(value = "/motor_running_data",method = {RequestMethod.POST})
    public List<MotorRunningData> getAllRunningData() {
        List<MotorRunningData> data = motorRunningDataDAO.selectRecently(20);
        if (data==null||data.isEmpty()){
            return new ArrayList<>();
        }
        Date now = new Date();
        if (Math.abs(now.getTime()-data.get(0).getRunTime().getTime())>18000){
            MotorRunningData nowData = new MotorRunningData();
            nowData.setnTrue("0");
            nowData.setiTrue("0");
            nowData.setRunTime(now);
            data.add(0,nowData);
            data.remove(data.size()-1);
        }
        return data;
    }
    /**
     * 最近的电机电流与转速运行数据，补零
     */
    @RequestMapping(value = "/motor_data_with_zero",method = {RequestMethod.POST})
    public List<MotorRunningData> getRecentRunningDataWithZero() {
        List<MotorRunningData> data = motorRunningDataDAO.selectRecently(6);
        if (data==null||data.isEmpty()){
            return Collections.emptyList();
        }
        Date now = new Date();
        if (now.getTime()-data.get(0).getRunTime().getTime()>10000){
            MotorRunningData nowData = new MotorRunningData();
            nowData.setnTrue("0");
            nowData.setiTrue("0");
            nowData.setRunTime(now);
            data.add(0,nowData);
        }
        for (int i = data.size()-2; i >= 0; i--) {
            int gap = (int)(data.get(i).getRunTime().getTime() - data.get(i+1).getRunTime().getTime());
            if (gap>50000){//50 ms
                //10秒补一个
                Date early = data.get(i+1).getRunTime();
                Date late = data.get(i).getRunTime();
                int n= gap/10000;
                long j=10000L;
                for (int k=0; k<n; k++){
                    MotorRunningData pad = new MotorRunningData();
                    if (early.getTime()+j==late.getTime()){
                        break;
                    }
                    pad.setRunTime(new Date(early.getTime()+j));
                    j+=10000L;
                    pad.setiTrue("0");
                    pad.setnTrue("0");
                    data.add(i+1,pad);
                }
            }
        }
        return data.subList(0,6);
    }

    /**
     * 历史曲线，有限条，没补零
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping(value = "/get_motor_data")
    public List<MotorRunningData> getRunningDataByDate(@RequestParam("start_time") String startTime,
                                                       @RequestParam("end_time") String endTime){
        try {
            if (startTime==null||startTime.length()==0||endTime==null||endTime.length()==0){
                return motorRunningDataDAO.selectRecently(20);
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

    /**
     * 根据起止时间获取历史传感器数据，并补0，无条数限制
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping(value = "/get_all_motor_data")
    public List<MotorRunningData> getAllRunningDataByDate(@RequestParam("start_time") String startTime,
                                                       @RequestParam("end_time") String endTime){
        if (startTime==null||startTime.length()==0||endTime==null||endTime.length()==0){
            return null;
        }
        Date start = null;
        Date end = null;
        try {
            start = stringToDate(startTime);
            end = stringToDate(endTime);
        } catch (ParseException e) {
            log.error("日期转换错误"+e);
            e.printStackTrace();
        }
        if (start==null||end==null) {
            return null;
        }
        List<MotorRunningData> data = motorRunningDataDAO.selectRangeAll(start,end);
        if(data == null){
            return null;
        }
        for (int i = data.size()-2; i >= 0; i--) {
            int gap = (int)(data.get(i).getRunTime().getTime() - data.get(i+1).getRunTime().getTime());
            if (gap>50000){//50 ms
                //10秒补一个
                Date early = data.get(i+1).getRunTime();
                Date late = data.get(i).getRunTime();
                int n= gap/10000;
                long j=10000L;
                for (int k=0; k<n; k++){
                    MotorRunningData pad = new MotorRunningData();
                    if (early.getTime()+j==late.getTime()){
                        break;
                    }
                    pad.setRunTime(new Date(early.getTime()+j));
                    j+=10000L;
                    pad.setiTrue("0");
                    pad.setnTrue("0");
                    data.add(i+1,pad);
                }
            }
        }
        return data;
    }
}
