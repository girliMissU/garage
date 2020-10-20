package com.garage.admin.controller;


import com.garage.admin.dao.LifeManageDAO;
import com.garage.admin.model.LifeManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class LifeManageController {

    @Autowired
    LifeManageDAO lifeManageDAO;

    //历史故障
    @RequestMapping(value = "/life_manage",method = {RequestMethod.POST})
    public List<LifeManage> getLifeManage(@Param("authority") int authority) {
        List<LifeManage> list = new ArrayList<LifeManage>();
        if(authority == 0){
            // 最高管理员，获取所有历史故障列表
            list = lifeManageDAO.selectAll();
        }else{
            list.add(lifeManageDAO.selectByGarageId(authority));
        }
        return list;
    }
}
