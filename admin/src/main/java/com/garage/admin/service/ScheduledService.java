package com.garage.admin.service;

import com.garage.admin.dao.FaultContactDAO;
import com.garage.admin.dao.FaultDataDAO;
import com.garage.admin.dao.FaultTriggerDAO;
import com.garage.admin.dao.FaultWarningDAO;
import com.garage.admin.model.FaultContact;
import com.garage.admin.model.FaultTrigger;
import com.garage.admin.model.FaultWarning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.garage.admin.service.SmsService.sendSms;

/**
 * @author LIFAN
 * 2019/3/16 12:06
 */

@Component
//1.主要用于标记配置类，兼备Component的效果。
@Configuration
// 2.开启定时任务
@EnableScheduling
public class ScheduledService {
    @Autowired
    FaultTriggerDAO faultTriggerDAO;

    @Autowired
    FaultContactDAO faultContactDAO;

    @Autowired
    FaultWarningDAO faultWarningDAO;

    @Autowired
    FaultDataDAO faultDataDAO;

    // 3.添加定时任务
    @Scheduled(cron = "0/10 * * * * ?")
    // 或直接指定时间间隔，例如：10秒
    // @Scheduled(fixedRate=5000)
    private void configureTasks() {
        //System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        List<FaultTrigger> triggerList = faultTriggerDAO.selectAll();
        String faultData = faultDataDAO.selectById(1).getData();

        for(FaultTrigger trigger : triggerList){
            //FaultWarning faultWarning = faultWarningDAO.selectById(trigger.getGarageId());
            //String FaultData = Integer.toString(faultWarning.getJiTing());
            char faultState = faultData.charAt(Integer.valueOf(trigger.getDataPoint()));
            //System.out.println(faultstate);
            if(faultState == '1'){
                FaultContact faultContact = faultContactDAO.selectById(trigger.getContactId());
                sendSms(faultContact.getPhoneNum(),faultContact.getUsername(),trigger.getFaultName());
                System.out.println("发送成功");
            }
        }
        faultDataDAO.updateData("00000");
    }
}
