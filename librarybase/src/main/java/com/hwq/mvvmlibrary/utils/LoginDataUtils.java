package com.hwq.mvvmlibrary.utils;


import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link LoginDataUtils}
 * Created by :WenqiangHuang
 * Create Time:2020-12-24 14:16
 * Email      :vieqqw@163.com
 */
public class LoginDataUtils {

    private static LoginDataUtils dataUtils = null;

    private LoginDataUtils() {

    }

    public static LoginDataUtils init() {
        if (dataUtils == null) {
            dataUtils = new LoginDataUtils();
        }
        return dataUtils;
    }


    private Map<String, String> headers;

    public Map<String, String> getHeaders() {

        if (headers == null) {
            return new HashMap<>();
        } else {
            return headers;
        }
    }

    public LoginDataUtils setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }


    public void builder() {

    }

}
