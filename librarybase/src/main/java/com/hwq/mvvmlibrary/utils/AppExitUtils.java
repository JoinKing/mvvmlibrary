package com.hwq.mvvmlibrary.utils;

import android.app.Activity;


/**
 * Created by 黄文强 on 2018/3/21.
 * 在主页时双击返回键退出
 */

public class AppExitUtils {
    private static long mExitTime;
    public static void exit(Activity activity){
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showShort("再次点击退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            activity.finish();
            System.exit(0);
        }
    }
}
