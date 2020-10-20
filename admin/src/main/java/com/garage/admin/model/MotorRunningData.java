package com.garage.admin.model;

import java.util.Date;

/**
 * @Author: LiFan
 * @Date: 2019/11/3
 */
public class MotorRunningData {
    private int id;
    private Date runTime;
    private String i;
    private String n;
    private String iTrue;
    private String nTrue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getiTrue() {
        return iTrue;
    }

    public void setiTrue(String iTrue) {
        this.iTrue = iTrue;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }



    public String getnTrue() {
        return nTrue;
    }

    public void setnTrue(String nTrue) {
        this.nTrue = nTrue;
    }
}
