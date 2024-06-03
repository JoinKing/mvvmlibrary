package com.hwq.mvvmlibrary.utils;

import com.hwq.mvvmlibrary.R;

/**
 * A simple {@link LoadingDialogUtils} subclass.
 * Created by WenqiangHuang
 * Creation time 2020/1/6
 * Email vieqqw@163.com
 */
public class LoadingDialogUtils {
    private static LoadingDialogUtils utils;

    private LoadingDialogUtils() {
    }

    public static LoadingDialogUtils getInstance() {
        if (utils == null) {
            utils = new LoadingDialogUtils();
        }
        return utils;
    }

    private int image = -1;

    public int getImage() {
        return image==-1? R.drawable.loading :image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
