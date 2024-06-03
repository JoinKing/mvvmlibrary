package com.hwq.mvvmlibrary.binding.adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * A simple {@link NoScrollLinnerLayoutManager} subclass.
 * Created by WenqiangHuang
 * Creation time 2020/7/21
 * Email vieqqw@163.com
 */
public class NoScrollLinnerLayoutManager extends LinearLayoutManager {

    private boolean isScrollEnabled = true;

    public NoScrollLinnerLayoutManager(Context context) {
        super(context);
    }


    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }

}
