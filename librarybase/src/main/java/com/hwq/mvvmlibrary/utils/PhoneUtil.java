package com.hwq.mvvmlibrary.utils;

import android.text.TextUtils;

/**
 * Created by king on 2017/3/29.
 */

public class PhoneUtil {

    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
        String num = "[1][2345789]\\d{9}";
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

}
