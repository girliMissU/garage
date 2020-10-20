package com.garage.admin.controller;

import com.garage.admin.dao.PersonDetectionDAO;
import com.garage.admin.dao.ScreenStatusDAO;
//import com.garage.admin.service.FaceDetectService;
import com.garage.admin.model.PersonDetection;
import com.garage.admin.service.FaceDetectService;
import com.garage.admin.service.HttpInterfaceService;
import com.garage.admin.util.GarageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LIFAN
 * 2018/12/4 15:08
 */
@RestController
@RequestMapping("/admin")
public class ImageProcessController {

    @Autowired
    FaceDetectService faceDetectService;

    @Autowired
    ScreenStatusDAO screenStatusDAO;

    @Autowired
    HttpInterfaceService httpInterfaceService;

    @Autowired
    PersonDetectionDAO personDetectionDAO;

//    //保存图片
//    @RequestMapping(value = "/get_image", method = {RequestMethod.POST})
//    public boolean getImage(@RequestParam("bases") String bases,
//                            @RequestParam("name") String name) {
//
//        System.out.println("原始字符串:" + bases);
//        String imgStr = bases.substring(bases.indexOf(",")+1);
//        /*System.out.println();
//        System.out.println();
//        System.out.println();*/
//        System.out.println("子字符串" + imgStr);
//        //将前端传过来的Base64解码并生成图片
//        return faceDetectService.GenerateImage(imgStr,name);
//    }

    //获取并保存图片
    @RequestMapping(value = "/yoloTest", method = {RequestMethod.POST,RequestMethod.GET})
    public String yoloTest() {
        String result;
        try{
            result = faceDetectService.test();
        } catch (Exception e){
            return e.toString();
        }
        return result;
    }

    //获取并保存图片
    @RequestMapping(value = "/getAndSaveImage", method = {RequestMethod.POST})
    public String getAndSaveImage(@RequestParam("imgName") String imgName) {
        return httpInterfaceService.getAndSaveImage(imgName);
    }

    //获取并保存图片
    @RequestMapping(value = "/getIfPerson", method = {RequestMethod.POST})
    public String getIfPerson() {
        PersonDetection personDetection =personDetectionDAO.selectDetection();
        return personDetection.getIfPerson();
    }

    //获取并保存图片
    @RequestMapping(value = "/getHistoryDetection", method = {RequestMethod.POST})
    public List<PersonDetection> getHistoryDetection() {
        List<PersonDetection> list =personDetectionDAO.selectHistroy();
        return list;
    }

    //获取截图标志位
    @RequestMapping(value = "/get_status", method = {RequestMethod.POST,RequestMethod.GET})
    public int getStatus() {
        return screenStatusDAO.selectById(1).getStatus();
    }
}
