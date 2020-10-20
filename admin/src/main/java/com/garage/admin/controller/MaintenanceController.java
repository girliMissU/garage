package com.garage.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.garage.admin.model.Maintenance;
import com.garage.admin.service.MaintenanceService;
import com.garage.admin.util.GarageUtil;

@RestController
@RequestMapping("/admin")
public class MaintenanceController {
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	/**
	 * 获取保养工单内容
	 */
	@RequestMapping(path = "/get_maintenance", method = {RequestMethod.POST})
	public List<Maintenance> getMaintenance(@RequestParam("username") String username){
//		System.out.println("路径拦截成功！");
		return maintenanceService.getLatestRecord(username, 0, 10);
	}
	
	@RequestMapping(path="/get_maintenance_by_id", method=RequestMethod.POST)
	public Maintenance getMaintenanceById(@RequestParam("id") Integer id) {
		return maintenanceService.getMaintenanceById(id);
	}
	
	@RequestMapping(path="/get_maintenance_by_status", method=RequestMethod.GET)
	public List<Maintenance> getMaintenanceByStatus(@RequestParam("username") String username){
		return maintenanceService.getLatestRecordByStatus(username, 0, 10);
	}
	
	/**
	 * 添加保养工单
	 */
	@RequestMapping(path = "/add_maintenance", method = {RequestMethod.POST})
	public String addMaintenance(@RequestParam("username") String username, 
			@RequestParam("garage_id") String garageId, 
            @RequestParam("content") String content, 
            @RequestParam("operator") String operator,
            @RequestParam("mtCompany") String mtCompany) {
		int flag = maintenanceService.addMaintenance(username,garageId,content,operator,mtCompany);
		if(flag > 0) {
			return GarageUtil.getJSONString(0);
		}else if(flag == -1){
			return GarageUtil.getJSONString(-1, "车库编号无效");
		}else {
			return GarageUtil.getJSONString(1, "添加失败");
		}
	}
	
	/**
	 * 删除工单
	 */
	@RequestMapping(path = "/delete_maintenance", method = {RequestMethod.GET})
	public String deleteMaintenance(@RequestParam("id") int id) {
		if(maintenanceService.deleteById(id)) {
			return GarageUtil.getJSONString(0);
		}else {
			return GarageUtil.getJSONString(1, "添加失败");
		}
	}
	
	/**
	 * 按筛选条件搜索
	 * consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = {"application/json; charset=UTF-8"
	 */
	@RequestMapping(path = "/searchByCondition", method = RequestMethod.POST )
	public List<Maintenance> getGarageInfoByCondition(@RequestParam(value="username") String username,
			@RequestParam(value="id",required = false) Integer id,
			@RequestParam(value="garage_id",required = false) String garageId,
            @RequestParam(value="content",required = false) String content,
			@RequestParam(value="startDate",required = false) String startDate,
			@RequestParam(value="endDate",required = false) String endDate,
            @RequestParam(value="operator",required = false) String operator,
            @RequestParam(value="status",required = false) String status,
            @RequestParam(value="manager",required = false) String manager,
            @RequestParam(value="mtCompany",required = false) String mtCompany){
		/**
		 * 如果未输入搜索条件则返回全部工单
		 */
		if(id==null&&garageId==null&&content==null&&startDate==null&&endDate==null&&operator==null&&status==null&&manager==null&&mtCompany==null) {
			return maintenanceService.getLatestRecord(username, 0, 10);
		}
		return maintenanceService.getAnyByCondition(id,garageId,content,startDate,endDate,operator,status,manager,mtCompany);
	}
	
	/**
	 * 更新工单处理结果
	 */
	@RequestMapping(path = "/update_maintenance", method = RequestMethod.POST)
	public String updateById(@RequestParam("id") Integer id,
			@RequestParam(value="endDate") String endDate,
			@RequestParam(value="log") String log) {
		if(maintenanceService.updateById(id,endDate,log)) {
			return GarageUtil.getJSONString(0);
		}else {
			return GarageUtil.getJSONString(1, "更新失败");
		}
	}
}
