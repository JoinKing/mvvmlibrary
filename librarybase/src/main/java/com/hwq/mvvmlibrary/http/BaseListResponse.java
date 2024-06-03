package com.hwq.mvvmlibrary.http;

import java.io.Serializable;
import java.util.List;

/**
 * Created by king on 2018.12.21
 * 该类仅供参考，实际业务返回的固定字段, 根据需求来定义，
 */
public class BaseListResponse<T> implements Serializable {
    private int code;
    private int status;
    private String msg;
    private List<T> data;

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseListResponse{" +
                "code=" + code +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
