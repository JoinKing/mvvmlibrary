package com.hwq.mvvmlibrary.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hwq.mvvmlibrary.R;

/**
 * A simple {@link BaseDialog} subclass.
 * Created by WenqiangHuang
 * Creation time 2020/7/31
 * Email vieqqw@163.com
 */
public abstract class BaseDialog extends Dialog {

    protected View view;

    private int gravity = Gravity.CENTER;

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.bottom_dialog);
    }

    public BaseDialog(@NonNull Context context,int gravity) {
        super(context, R.style.bottom_dialog);
        this.gravity = gravity;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        init(gravity);
        initData();
    }

    protected abstract int initContentView();

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public abstract void initData();

    /**
     * 初始化
     *
     * @param gravity
     */
    protected void init(int gravity){
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        window.setGravity(gravity);
    }


}
