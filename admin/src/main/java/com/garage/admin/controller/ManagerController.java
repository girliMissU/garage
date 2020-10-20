package com.garage.admin.controller;

import com.garage.admin.model.Manager;
import com.garage.admin.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    //添加新的管理员
    @RequestMapping(value = "/add_manager",method = {RequestMethod.POST})
    public String add_manager(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("authority") int authority) {
        return managerService.register(username,password,authority);
    }

    //查看所有管理员
    @RequestMapping(value = "/manager_view",method = {RequestMethod.GET})
    public List<Manager> managerView() {
        List<Manager> list = new ArrayList<Manager>();
        list = managerService.managerView();
        return list;
    }
}
