package com.garage.admin.model;

import java.util.Date;

/**
 * @Author: LiFan
 * @Date: 2020/6/17
 */
public class PersonDetection {
    private int id;
    private String garageId;
    private String ifPerson;
    private Date sendTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGarageId() {
        return garageId;
    }

    public void setGarageId(String garageId) {
        this.garageId = garageId;
    }

    public String getIfPerson() {
        return ifPerson;
    }

    public void setIfPerson(String ifPerson) {
        this.ifPerson = ifPerson;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date runTime) {
        this.sendTime = runTime;
    }
}