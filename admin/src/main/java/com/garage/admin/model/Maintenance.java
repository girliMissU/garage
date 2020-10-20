package com.garage.admin.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 保养工单
 * @author Administrator
 *
 */
public class Maintenance implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//保养工单编号
	private String garageId;
	private String address;
	private String content;//保养类型
	private Date startDate;
	private Date endDate;
	private String status;
	private String maintainCompany;//维保单位
	private String operator;//维保人员
	private String manager;//建单人
	private String log;//保养日志

	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGarageId() {
		return garageId;
	}
	public void setGarageId(String garageId) {
		this.garageId = garageId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMaintainCompany() {
		return maintainCompany;
	}
	public void setMaintainCompany(String maintainCompany) {
		this.maintainCompany = maintainCompany;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	@Override
	public String toString() {
		return "Maintenance [id=" + id + ", garageId=" + garageId + ", address=" + address + ", content=" + content
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", maintainCompany="
				+ maintainCompany + ", operator=" + operator + ", manager=" + manager + ", log=" + log + "]";
	}

	
}
