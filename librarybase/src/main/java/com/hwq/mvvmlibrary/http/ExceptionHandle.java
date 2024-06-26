package com.hwq.mvvmlibrary.http;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.hwq.mvvmlibrary.utils.ToastUtils;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;


/**
 * Created by king on 2018.12.21
 */
public class ExceptionHandle {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int SERVICE_UNAVAILABLE = 503;

    private static final int SUCCESS = 100;
    private static final int SUCCESS_ADD = 101;
    private static final int SUCCESS_DELETE = 102;
    private static final int SUCCESS_UPDATE = 103;
    private static final int SUCCESS_QUERY = 104;
    private static final int FAILED = 200;
    private static final int FAILED_ADD = 201;
    private static final int FAILED_DELETE = 202;
    private static final int FAILED_UPDATE = 203;
    private static final int FAILED_QUERY = 204;


    public static ResponseThrowable handleException(Throwable e) {
        ToastUtils.showLong(e.getMessage());
        ResponseThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponseThrowable(e, ERROR.HTTP_ERROR);
            ex.code = httpException.code()+"";
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message = e.getMessage();
                    break;
                case FORBIDDEN:
                    ex.message = "请求被拒绝";
                    break;
                case NOT_FOUND:
                    ex.message = "资源不存在";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "服务器执行超时";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = "服务器内部错误";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = "服务器不可用";
                    break;
                default:
                    ex.message = "网络连接不正常，似乎已断开";
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {
            ex = new ResponseThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponseThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "网络连接不正常，似乎已断开";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLException) {
            ex = new ResponseThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else if (e instanceof java.net.UnknownHostException) {
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "主机地址未知";
            return ex;
        } else {
            ex = new ResponseThrowable(e, ERROR.UNKNOWN);
            ex.message = "未知错误";
            return ex;
        }
    }


    /**
     * 约定异常 这个具体规则需要与服务端或者领导商讨定义
     */
    public static class ERROR {
        /**
         * 未知错误
         */
        public static final String UNKNOWN = "1000";
        /**
         * 解析错误
         */
        public static final String PARSE_ERROR = "1001";
        /**
         * 网络错误
         */
        public static final String NETWORD_ERROR = "1002";
        /**
         * 协议出错
         */
        public static final String HTTP_ERROR = "1003";

        /**
         * 证书出错
         */
        public static final String SSL_ERROR = "1005";

        /**
         * 连接超时
         */
        public static final String TIMEOUT_ERROR = "1006";
    }

}

