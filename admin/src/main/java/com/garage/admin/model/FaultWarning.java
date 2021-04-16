package com.garage.admin.model;

public class FaultWarning {
    private int id;
    private int jiTing;
    private int guangDian;
    private int reJiGuoZai;
    private int duanDian;
    private int fangSongLian;
    private int jiXian;
    //private int guaGou;
    //private int xiangXu;
    private int rotate;
    private int current;

    @Override
    public String toString() {
        return "FaultWarning{" +
                "id=" + id +
                ", jiTing=" + jiTing +
                ", guangDian=" + guangDian +
                ", reJiGuoZai=" + reJiGuoZai +
                ", duanDian=" + duanDian +
                ", fangSongLian=" + fangSongLian +
                ", jiXian=" + jiXian +
                ", rotate=" + rotate +
                ", current=" + current +
                '}';
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public FaultWarning(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJiTing() {
        return jiTing;
    }

    public void setJiTing(int jiTing) {
        this.jiTing = jiTing;
    }

    public int getGuangDian() {
        return guangDian;
    }

    public void setGuangDian(int guangDian) {
        this.guangDian = guangDian;
    }

    public int getReJiGuoZai() {
        return reJiGuoZai;
    }

    public void setReJiGuoZai(int reJiGuoZai) {
        this.reJiGuoZai = reJiGuoZai;
    }

    public int getDuanDian() {
        return duanDian;
    }

    public void setDuanDian(int duanDian) {
        this.duanDian = duanDian;
    }

    public int getFangSongLian() {
        return fangSongLian;
    }

    public void setFangSongLian(int fangSongLian) {
        this.fangSongLian = fangSongLian;
    }

    public int getJiXian() {
        return jiXian;
    }

    public void setJiXian(int jiXian) {
        this.jiXian = jiXian;
    }

}
