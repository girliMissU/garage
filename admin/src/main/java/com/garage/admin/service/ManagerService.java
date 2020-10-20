package com.garage.admin.service;

import com.garage.admin.dao.LoginTicketDAO;
import com.garage.admin.dao.ManagerDAO;
import com.garage.admin.model.LoginTicket;
import com.garage.admin.model.Manager;
import com.garage.admin.util.GarageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ManagerService {

    @Autowired
    private ManagerDAO managerDAO;

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    public Manager getUser(int id) {
        return managerDAO.selectById(id);
    }

    public Manager getUserByUsername(String username) {
        return managerDAO.selectByUserName(username);
    }

    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        Manager manager = managerDAO.selectByUserName(username);
        if (manager == null) {
            //用户不存在
            map.put("status",-2);
            return map;
        }

        if(!GarageUtil.MD5(password+ manager.getSalt()).equals(manager.getPassword())){
            //密码错误
            map.put("status",-1);
            return map;
        }

        String ticket = addLoginTicket(manager.getId());
        int authority = manager.getAuthority();
        map.put("status",1);
        map.put("ticket",ticket);
        map.put("authority",authority);
        return map;
    }

    //添加登录ticket
    private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.addTicket(ticket);
        return ticket.getTicket();
    }

    //判断ticket是否有效
    public String ifTicket(String ticket) {
        LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
        if (loginTicket != null && loginTicket.getExpired().after(new Date()) && loginTicket.getStatus() != 1) {
            return "1";
        }
        return "0";
    }

    //注销登录
    public void logout(String ticket) {
        loginTicketDAO.updateStatus(ticket, 1);
    }

    //注册新管理员
    public String register(String username, String password, int authority) {
        Manager manager = managerDAO.selectByUserName(username);
        if (manager != null) {
            //用户名已经被注册
            return GarageUtil.getJSONString(-1,"用户已注册");
        }else{
            //新管理员存入数据库
            manager = new Manager();
            manager.setUsername(username);
            manager.setSalt(UUID.randomUUID().toString().substring(0, 5));
            manager.setPassword(GarageUtil.MD5(password+ manager.getSalt()));
            manager.setAuthority(authority);
            managerDAO.addUser(manager);
            return GarageUtil.getJSONString(0,"成功");
        }
    }

    public List<Manager> managerView(){
        List<Manager> list = new ArrayList<Manager>();
        list = managerDAO.selectAll();
        return list;
    }
}
