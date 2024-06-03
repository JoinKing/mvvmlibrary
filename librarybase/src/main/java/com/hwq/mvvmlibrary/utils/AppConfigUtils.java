package com.hwq.mvvmlibrary.utils;

import com.hwq.mvvmlibrary.R;

import java.util.Map;

/**
 * A simple {@link AppConfigUtils} subclass.
 * Created by WenqiangHuang
 * Creation time 2020/1/6
 * Email vieqqw@163.com
 */
public class AppConfigUtils {
    private static AppConfigUtils utils;

    private AppConfigUtils() {
    }

    public static AppConfigUtils getInstance() {
        if (utils == null) {
            utils = new AppConfigUtils();
        }
        return utils;
    }

    private int image = -1;
    private int barColor = -1;
    private int barTextColor = -1;
    private int barAlpha = -1;
    private boolean isBarColor;

    public static final int COLOR_BLACK = 0X001;//状态栏字体为黑色
    public static final int COLOR_WHITE = 0X002;//状态栏字体为白色


    public static void setUtils(AppConfigUtils utils) {
        AppConfigUtils.utils = utils;
    }

    public int getBarTextColor() {
        return barTextColor;
    }

    public AppConfigUtils setBarTextColor(int barTextColor) {
        this.barTextColor = barTextColor;
        return this;
    }

    public int getBarAlpha() {
        return barAlpha;
    }

    public AppConfigUtils setBarAlpha(int barAlpha) {
        this.barAlpha = barAlpha;
        return this;
    }

    public boolean isBarColor() {
        return isBarColor;
    }

    public AppConfigUtils setBarColor(boolean barColor) {
        isBarColor = barColor;
        return this;
    }

    public int getImage() {
        return image == -1 ? R.drawable.loading : image;
    }

    public AppConfigUtils setImage(int image) {
        this.image = image;
        return this;
    }

    public int getBarColor() {
        return barColor == -1 ? 0X001 : barColor;
    }

    public AppConfigUtils setBarColor(int barColor) {
        this.barColor = barColor;
        return this;
    }

    public AppConfigUtils builder() {
        return this;
    }


}
