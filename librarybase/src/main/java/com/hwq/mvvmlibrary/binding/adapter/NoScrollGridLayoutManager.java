package com.hwq.mvvmlibrary.binding.adapter;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;

/**
 * A simple {@link NoScrollGridLayoutManager} subclass.
 * Created by WenqiangHuang
 * Creation time 2020/7/21
 * Email vieqqw@163.com
 */
public class NoScrollGridLayoutManager extends GridLayoutManager {

    private boolean isScrollEnabled = true;

    public NoScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }


    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }

}
