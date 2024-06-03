package com.hwq.mvvmlibrary.utils;

import android.util.Log;

/**
 * Created by cxf on 2017/8/3.
 */

public class L {

    public static boolean sDeBug;

    private final static String TAG = "log--->";

    public static void e(String s) {
        e(TAG, s);
    }

    public static void d(String s) {
        e(TAG, s);
    }

    public static void e(String tag, String s) {
        if (sDeBug) {
            Log.e(tag, s);
        }
    }

    public static void printlnAll(String tag, String[] info) {
        if (info[0].length() > 4000){
            for (int i = 0; i < info[0].length(); i += 4000) {
                //当前截取的长度<总长度则继续截取最大的长度来打印
                if (i + 4000 < info[0].length()) {
                    Log.i(tag + i, info[0].substring(i, i + 4000));
                } else {
                    //当前截取的长度已经超过了总长度，则打印出剩下的全部信息
                    Log.i(tag + i, info[0].substring(i, info[0].length()));
                }
            }
        }else{
            Log.d(tag,info[0]);
        }
    }

    public static void d(String tag, String s) {
        if (sDeBug) {
            Log.d(tag, s);
        }
    }

    public static void setDeBug(boolean deBug) {
        sDeBug = deBug;
    }
}
