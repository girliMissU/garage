package com.garage.admin.controller;

import com.garage.admin.dao.HistorySolutionDAO;
import com.garage.admin.model.HistorySolution;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class HistorySolutionController {
    @Autowired
    HistorySolutionDAO historySolutionDAO;

    //历史故障
    @RequestMapping(value = "/all_history_solution",method = {RequestMethod.POST})
    public List<HistorySolution> getAllHistoryFault(@Param("authority") int authority) {
        List<HistorySolution> list = new ArrayList<HistorySolution>();
        if(authority == 0){
            // 最高管理员，获取所有历史故障列表
            list = historySolutionDAO.selectAll();
        }else{
            list.add(historySolutionDAO.selectByGarageId(authority));
        }
        return list;
    }
}
