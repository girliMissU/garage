package com.garage.admin.model;

import java.util.Date;

public class Solution {
    private int id;
    private int faultCode;
    private Date dealTime;
    private String dealManager;
    private String solution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(int faultCode) {
        this.faultCode = faultCode;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealManager() {
        return dealManager;
    }

    public void setDealManager(String dealManager) {
        this.dealManager = dealManager;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
