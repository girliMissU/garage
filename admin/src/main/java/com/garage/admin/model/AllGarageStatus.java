package com.garage.admin.model;

public class AllGarageStatus {
    private int totalNum;
    private int usedNum;
    private int notUsedNum;
    private int faultNum;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(int usedNum) {
        this.usedNum = usedNum;
    }

    public int getNotUsedNum() {
        return notUsedNum;
    }

    public void setNotUsedNum(int notUsedNum) {
        this.notUsedNum = notUsedNum;
    }

    public int getFaultNum() {
        return faultNum;
    }

    public void setFaultNum(int faultNum) {
        this.faultNum = faultNum;
    }
}
