package com.hwq.mvvmlibrary.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwq.mvvmlibrary.R;


/**
 * A simple {@link LoadingDialog} subclass.
 * Created by WenqiangHuang
 * Creation time 2019/8/26
 * Email vieqqw@163.com
 */
public class LoadingDialog extends Dialog {

    private ImageView imLoad;
    private Context context;
    private Animation drawable;
    private TextView tvTitle;
    private String message = "";


    public boolean isShowing;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.bottom_dialog);
        this.context = context;

    }

    public void setMessage(String message) {
        this.message = message;
        if (tvTitle!=null){
            tvTitle.setText(message);
        }

    }

    private int drawables;

    public void setDrawable(int drawable) {
        drawables = drawable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        setCancelable(false);
        getWindow().setDimAmount(0f);
        initView();
    }

    public void showAnimation(){
        show();
    }


    @Override
    public void show() {
        if (null!=imLoad){
            imLoad.startAnimation(drawable);
        }
        isShowing = true;
        super.show();
    }

    public void dissAnimation(){
        if (null!=imLoad){
            imLoad.clearAnimation();
        }
        isShowing = false;
        this.dismiss();
    }

    public void initView(){
        imLoad = findViewById(R.id.imLoad);
        tvTitle = findViewById(R.id.tvTitle);
        imLoad.setImageResource(drawables);
        tvTitle.setText(message.isEmpty() ? "" : message);
        drawable = AnimationUtils.loadAnimation(context, R.anim.dialog_loading);
        imLoad.startAnimation(drawable);
    }
}
