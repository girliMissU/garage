package com.garage.admin.controller;

import com.garage.admin.service.ManagerService;
import com.garage.admin.util.GarageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private ManagerService managerService;

    //判断用户名密码是否正确
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletResponse response) {
        Map<String, Object> map = managerService.login(username,password);
        //存入用户ticket到cookie中
        if (map.containsKey("ticket")){
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            //System.out.println(cookie);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        if (map.containsKey("authority")){
            Cookie cookie = new Cookie("authority", map.get("authority").toString());
            //System.out.println(cookie);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

//      System.out.println(back);
        return GarageUtil.getJSONString(0, map);
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(@CookieValue("ticket") String ticket) {
        managerService.logout(ticket);
        return "1";
    }

    @RequestMapping(value = "/ifTicket",method = {RequestMethod.POST})
    public String ifTicket(@RequestParam("ticket") String ticket) {
        String ifTicket = managerService.ifTicket(ticket);
        return ifTicket;
    }

}


