package com.garage.admin.controller;

import com.garage.admin.dao.AllGarageStatusDAO;
import com.garage.admin.dao.CarPlaceDAO;
import com.garage.admin.dao.GarageInfoDAO;
import com.garage.admin.model.AllGarageStatus;
import com.garage.admin.model.CarPlace;
import com.garage.admin.model.GarageInfo;
import com.garage.admin.model.LoginTicket;
import com.garage.admin.util.GarageUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/admin")
public class AllGarageController {
    private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    private AllGarageStatusDAO allGarageStatusDAO;

    @Autowired
    private GarageInfoDAO garageInfoDAO;

    @Autowired
    private CarPlaceDAO carPlaceDAO;

    //车库总览
    @RequestMapping(value = "/get_all_garage",method = {RequestMethod.GET})
    public AllGarageStatus getAllGarage() {
        AllGarageStatus allGarageStatus = allGarageStatusDAO.selectById(1);
        return allGarageStatus;
    }

    //实时监控车库具体信息
    @RequestMapping(value = "/get_all_garage_info",method = {RequestMethod.POST})
    public List<GarageInfo> getAllGarageInfo(@RequestParam("authority") int authority) {

        List<GarageInfo> list = new ArrayList<GarageInfo>();
        if(authority == 0) {
            // 权限为最高管理员，获取车库列表
            list = garageInfoDAO.selectAll();
        }else{
            list.add(garageInfoDAO.selectById(authority));
        }
        return list;
    }

    //实时监控各个车库具体信息
    @RequestMapping(value = "/get_garage_info_by_id",method = {RequestMethod.POST})
    public GarageInfo getGarageInfoById(@RequestParam("id") int id) {
        GarageInfo garageInfo = garageInfoDAO.selectById(id);
        return garageInfo;
    }

    //根据省份与城市查询车库具体信息
    @RequestMapping(value = "/searchByArea",method = {RequestMethod.POST})
    public List<GarageInfo> getGarageInfoByArea(@RequestParam("province") String province,
                                        @RequestParam("city") String city) {
        return garageInfoDAO.selectByArea(province,city);
    }

    //修改车库具体信息
    @RequestMapping(value = "/change_garage_info",method = {RequestMethod.POST})
    public String changeGarageInfoById(@Param("garageId") int garageId,
                                    @Param("address") String address,
                                    @Param("totalNum") int totalNum,
                                    @Param("freeNum") int freeNum,
                                    @Param("latitude") float latitude,
                                    @Param("longtitude") float longtitude,
                                    @Param("pricePerHour") float pricePerHour) {
        try {
            if ( garageInfoDAO.updateGarageInfo(garageId,address,totalNum,freeNum,latitude,longtitude,pricePerHour) > 0) {
                return GarageUtil.getJSONString(0);
            }
        } catch (Exception e) {
            logger.error("修改信息失败" + e.getMessage());
        }
        return GarageUtil.getJSONString(1, "失败");

    }

    //实时监控各个车库车位状态信息
    @RequestMapping(value = "/get_car_place",method = {RequestMethod.POST})
    public List<CarPlace> getCarPlace() {
        List<CarPlace> list = new ArrayList<CarPlace>();
        // 获取车库列表
        list = carPlaceDAO.selectAll();
        return list;
    }


    //添加车库
    @RequestMapping(value = "/add_garage",method = {RequestMethod.POST})
    public int addGarage(@RequestParam("latitude") float latitude,
                         @RequestParam("longtitude") float longtitude,
                         @RequestParam("address") String address,
                         @RequestParam("total_num") int totalNum,
                         @RequestParam("free_num") int freeNum,
                         @RequestParam("price_per_hour") float pricePerHour) {
        int back = 0;
        GarageInfo garageInfo = new GarageInfo();
        garageInfo.setLatitude(latitude);
        garageInfo.setLongtitude(longtitude);
        garageInfo.setAddress(address);
        garageInfo.setTotalNum(totalNum);
        garageInfo.setFreeNum(freeNum);
        garageInfo.setPricePerHour(pricePerHour);
        back = garageInfoDAO.addGarage(garageInfo);
        return back;
    }


}
