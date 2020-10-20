package com.garage.admin.model;

import java.util.Date;

public class HistorySolution {
    private int id;
    private String solutionId;
    private int garageId;
    private String garageCode;
    private String faultType;
    private String faultContent;
    private Date dealTime;
    private String dealManager;
    private String solution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }

    public String getGarageCode() {
        return garageCode;
    }

    public void setGarageCode(String garageCode) {
        this.garageCode = garageCode;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getFaultContent() {
        return faultContent;
    }

    public void setFaultContent(String faultContent) {
        this.faultContent = faultContent;
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
