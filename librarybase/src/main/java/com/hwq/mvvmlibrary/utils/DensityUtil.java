package com.hwq.mvvmlibrary.utils;

import android.content.Context;

public class DensityUtil {
    public DensityUtil() {
    }
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F * (float)(dpValue >= 0.0F ? 1 : -1));
    }
    public static int dimenDip2px(Context context, int dimenId) {
        float value = context.getResources().getDimension(dimenId);
        return dip2px(context, value / context.getResources().getDisplayMetrics().density);
    }
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F * (float)(pxValue >= 0.0F ? 1 : -1));
    }
    public static int px2sp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / scale + 0.5F * (float)(pxValue >= 0.0F ? 1 : -1));
    }
    public static int sp2px(Context context, float spValue) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * scale + 0.5F * (float)(spValue >= 0.0F ? 1 : -1));
    }
}





