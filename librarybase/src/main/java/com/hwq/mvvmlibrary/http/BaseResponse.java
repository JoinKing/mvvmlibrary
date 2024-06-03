package com.hwq.mvvmlibrary.http;

import java.io.Serializable;


/**
 * A simple {@link BaseResponse}
 * Created by :WenqiangHuang
 * Create Time:2020-12-23 15:27
 * Email      :vieqqw@163.com
 */
public class BaseResponse<T> implements Serializable {
    public int status;
    public String msg;
    public T data;
}
