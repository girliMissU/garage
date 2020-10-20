package com.garage.admin.model;


public class ResultInfo {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;


    public static ResultInfo build(Integer status, String msg, Object data) {
        return new ResultInfo(status, msg, data);
    }

    public static ResultInfo build(Integer status, String msg) {
        return new ResultInfo(status, msg, null);
    }

    public static ResultInfo ok(Object data) {
        return new ResultInfo(data);
    }


    public ResultInfo() {

    }

    public ResultInfo(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultInfo(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
