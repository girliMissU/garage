package com.garage.admin.controller;

import com.garage.admin.dao.FaultContactDAO;
import com.garage.admin.dao.FaultHistoryDAO;
import com.garage.admin.dao.FaultTriggerDAO;
import com.garage.admin.dao.FaultWarningDAO;
import com.garage.admin.model.FaultContact;
import com.garage.admin.model.FaultHistory;
import com.garage.admin.model.FaultTrigger;
import com.garage.admin.model.FaultWarning;
import com.garage.admin.util.GarageUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
