package com.garage.admin.controller;

import com.garage.admin.dao.*;
import com.garage.admin.model.*;
import com.garage.admin.service.HttpInterfaceService;
import com.garage.admin.util.GarageUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin")
public class FaultWarningController {
    private static final Logger logger = LoggerFactory.getLogger(FaultWarningController.class);

    @Autowired
    FaultWarningDAO faultWarningDAO;

    @Autowired
    FaultHistoryDAO faultHistoryDAO;

    @Autowired
    FaultContactDAO faultContactDAO;

    @Autowired
    FaultTriggerDAO faultTriggerDAO;

    @Autowired
    MotorRunningDataDAO motorRunningDataDAO;

    @Autowired
    HttpInterfaceService httpService;

    //故障总览
    @RequestMapping(value = "/all_fault",method = {RequestMethod.POST})
    public List<FaultWarning> getAllGarage(@Param("authority") int authority) {
        List<FaultWarning> list = new ArrayList<FaultWarning>();
        if(authority == 0){
            // 最高管理员，获取所有故障报警列表
            list = faultWarningDAO.selectAll();
        }else{
            list.add(faultWarningDAO.selectById(authority));
        }
        return list;
    }

    @RequestMapping(value = "/fault",method = {RequestMethod.GET})
    public List<FaultWarning> getFaultNow(@Param("authority") int authority) throws InterruptedException {
        /*//获取最新电机数据
        final List<MotorRunningData> data = motorRunningDataDAO.selectRecently(1);
        Date now = new Date();
        if (now.getTime()-data.get(0).getRunTime().getTime()>10000){
            //发送0数据，0肯定正常啊？！
            faultWarningDAO.updateFaultResult(1,1,0);
            return faultWarningDAO.selectAll();
        }
        //向算法服务器发送请求
        Thread thread = new Thread(() -> {
            Map<String,String> params = new HashMap<>(2);
            params.put("R",data.get(0).getnTrue());
            params.put("I",data.get(0).getiTrue());
            final String response = httpService.sendPost("http://127.0.0.1:80",params);
            //处理结果并更新数据库fault_warning
            System.out.println(response);

        });
        thread.start();
        TimeUnit.SECONDS.sleep(3);*/
        //获取数据返回
        List<FaultWarning> list = faultWarningDAO.selectAll();
        return list;
    }

    //历史故障
    @RequestMapping(value = "/all_history_fault",method = {RequestMethod.POST})
    public List<FaultHistory> getAllHistoryFault(@Param("authority") int authority) {
        List<FaultHistory> list = new ArrayList<FaultHistory>();
        if(authority == 0){
            // 最高管理员，获取所有历史故障列表
            list = faultHistoryDAO.selectAll();
        }else{
            list.add(faultHistoryDAO.selectByGarageId(authority));
        }
        return list;
    }

    //添加故障联系人
    //http://www.garagecloud.cn:8080/admin/add_contact
    @RequestMapping(value = "/add_contact",method = {RequestMethod.POST})
    public String addContact(@RequestParam("username") String username,
                             @RequestParam("phone_num") String phoneNum,
                             @RequestParam("email") String email,
                             @RequestParam("exts") String exts) {
        try{
            FaultContact faultContact = new FaultContact();
            faultContact.setUsername(username);
            faultContact.setPhoneNum(phoneNum);
            faultContact.setEmail(email);
            faultContact.setExts(exts);
            faultContactDAO.addContact(faultContact);
            return GarageUtil.getJSONString(0);
        }catch (Exception e){
            logger.error("增加联系人失败",e.getMessage());
        }
        return GarageUtil.getJSONString(1, "失败");
    }

    //报警联系人列表
    @RequestMapping(value = "/fault_contact_list",method = {RequestMethod.POST})
    public List<FaultContact> getAllContact() {
        return faultContactDAO.selectAll();
    }

    //添加触发器
    @RequestMapping(value = "/add_trigger",method = {RequestMethod.POST})
    public String addTrigger(@RequestParam("garage_id") int garageId,
                             @RequestParam("fault_name") String faultName,
                             @RequestParam("data_point") String dataPoint,
                             @RequestParam("trigger_condition") String triggerCondition,
                             @RequestParam("alarm_way") String alarmWay,
                             @RequestParam("contact_id") int contactId){
                             //@RequestParam("trigger_status") int triggerStatus
                             //@RequestParam("update_time") Date updateTime)

        try{
            FaultTrigger faultTrigger = new FaultTrigger();
            faultTrigger.setGarageId(garageId);
            faultTrigger.setFaultName(faultName);
            faultTrigger.setDataPoint(dataPoint);
            faultTrigger.setTriggerCondition(triggerCondition);
            faultTrigger.setAlarmWay(alarmWay);
            faultTrigger.setContactId(contactId);
            faultTrigger.setTriggerStatus(1);
            faultTrigger.setUpdateTime(new Date());
            faultTriggerDAO.addTrigger(faultTrigger);
            return GarageUtil.getJSONString(0);
        }catch (Exception e){
            logger.error("增加触发器失败",e.getMessage());
        }
        return GarageUtil.getJSONString(1, "失败");
    }

    //触发器列表
    @RequestMapping(value = "/fault_trigger_list",method = {RequestMethod.POST})
    public List<FaultTrigger> triggerList(){
        return faultTriggerDAO.selectAll();
    }
}
