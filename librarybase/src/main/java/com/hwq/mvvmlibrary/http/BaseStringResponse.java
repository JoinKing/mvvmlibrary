package com.hwq.mvvmlibrary.http;

import java.io.Serializable;


/**
 * A simple {@link BaseStringResponse}
 * Created by :WenqiangHuang
 * Create Time:2020-12-23 15:27
 * Email      :vieqqw@163.com
 */
public class BaseStringResponse<T> implements Serializable {
    private int code;
    private int status;
    private String msg;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseStringResponse{" +
                "code=" + code +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
