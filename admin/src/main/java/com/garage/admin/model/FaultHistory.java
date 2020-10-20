package com.garage.admin.model;

import java.util.Date;

public class FaultHistory {
    private int id;
    private String faultId;
    private int garageId;
    private String garageCode;
    private String address;
    private int jiechuState;
    private String faultType;
    private int jianxiuState;
    private Date submitTime;
    private Date jiechuTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaultId() {
        return faultId;
    }

    public void setFaultId(String faultId) {
        this.faultId = faultId;
    }

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public String getGarageCode() {
        return garageCode;
    }

    public void setGarageCode(String garageCode) {
        this.garageCode = garageCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getJiechuState() {
        return jiechuState;
    }

    public void setJiechuState(int jiechuState) {
        this.jiechuState = jiechuState;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public int getJianxiuState() {
        return jianxiuState;
    }

    public void setJianxiuState(int jianxiuState) {
        this.jianxiuState = jianxiuState;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getJiechuTime() {
        return jiechuTime;
    }

    public void setJiechuTime(Date jiechuTime) {
        this.jiechuTime = jiechuTime;
    }
}
