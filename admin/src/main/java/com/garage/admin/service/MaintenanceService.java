package com.garage.admin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garage.admin.dao.GarageInfoDAO;
import com.garage.admin.dao.MaintenanceDao;
import com.garage.admin.model.GarageInfo;
import com.garage.admin.model.Maintenance;


@Service
public class MaintenanceService {
	
    @Autowired
    MaintenanceDao maintenanceDao;
    
    @Autowired
    GarageInfoDAO garageInfoDAO;

    /**
     * 获取一段时间的保养记录
     * @param username
     * @param offset
     * @param limit
     * @return
     */
    public List<Maintenance> getLatestRecord(String username, int offset, int limit) {
//        replyDAO.updateStatus(username);
        return maintenanceDao.selectLatestRecord(username,offset,limit);
    }
    
	public Maintenance getMaintenanceById(Integer id) {
		return maintenanceDao.selectById(id);
	}
	
	public List<Maintenance> getLatestRecordByStatus(String username, int offset, int limit) {
		return maintenanceDao.selectLatestRecordByStatus(username,offset,limit);
	}

    /**
     * 创建保养工单
     * @param operator 维保人员
     * @param content 维保类型
     * @param garageId 车库编号
     * @param username 建单人
     * @return 代表创建失败返回0，成功返回工单id，车库编号无效返回-1
     */
	public int addMaintenance(String username, String garageId, String content, String operator, String mtCompany) {
		Maintenance mt = new Maintenance();
		mt.setGarageId(garageId);
		mt.setContent(content);
		mt.setStartDate(new Date());
		mt.setManager(username);
		mt.setStatus("计划中");
		mt.setMaintainCompany(mtCompany);
		mt.setOperator(operator);
		/**
		 * 根据所填车库编号获取车库地址
		 */
		GarageInfo garageInfo = garageInfoDAO.selectByGarageCode(garageId);
		if(garageInfo==null) {
			return -1;
		}
		mt.setAddress(garageInfo.getAddress());
		return maintenanceDao.addMaintenance(mt)>0?mt.getId():0;
	}

	/**
	 * 删除指定工单
	 * @param id 指定工单的唯一id
	 * @return
	 */
	public boolean deleteById(Integer id) {
		try {
			maintenanceDao.deleteById(id);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 根据筛选条件获取工单
	 * @param 
	 * @return
	 */
	public List<Maintenance> getAnyByCondition(Integer id, String garageId, String content, String startDate, String endDate,
			String operator, String status, String manager, String mtCompany) {
		Maintenance maint = new Maintenance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		maint.setId(id);
		maint.setContent("%"+content+"%");
		try {
			if(startDate!="") {
				maint.setStartDate(sdf.parse(startDate+":00"));
			}
			if(endDate!="") {
				maint.setEndDate(sdf.parse(endDate+":00"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return new ArrayList<>(0);
		}
		maint.setStatus("%"+status+"%");
		maint.setMaintainCompany("%"+mtCompany+"%");
		System.out.println(maint);
		return maintenanceDao.selectByCondition(maint);
	}

	public boolean updateById(Integer id, String endDate, String log) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		try {
			maintenanceDao.updateById(id,sdf.parse(endDate+":00"),log);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
