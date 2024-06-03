package com.hwq.mvvmlibrary.http;

/**
 * Created by king on 2018.12.21
 */

public class ResponseThrowable extends Exception {
    public String code;
    public String message;

    public ResponseThrowable(Throwable throwable, String code) {
        super(throwable);
        this.code = code;
    }
}
