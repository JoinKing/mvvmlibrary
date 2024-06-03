package com.hwq.mvvmlibrary.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.hwq.mvvmlibrary.R;

public class ErBaseTitleView extends ConstraintLayout {
    AppCompatTextView tv_title,tv_right;
    LinearLayoutCompat erIlBack;

    public ErBaseTitleView(Context context) {
        super(context);
        initView();
    }

    public ErBaseTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ErBaseTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        View view = View.inflate(getContext(), R.layout.er_layout_base_title, this);
        tv_title = (AppCompatTextView) view.findViewById(R.id.erTvBaseTitle);
        tv_right = (AppCompatTextView) view.findViewById(R.id.erRightActionText);
        erIlBack = (LinearLayoutCompat) view.findViewById(R.id.erIlBack);
    }

    public ErBaseTitleView setTitleString(String title) {
        tv_title.setText(title);
        return this;
    }
    public ErBaseTitleView setTitileListener(OnClickListener clickListener) {
        tv_title.setOnClickListener(clickListener);
        return this;
    }

    public ErBaseTitleView setRightString(String string) {
        tv_right.setText(string);
        return this;
    }
    public ErBaseTitleView setRightDrawable(Drawable background) {
        tv_right.setBackgroundDrawable(background);
        return this;
    }
    public ErBaseTitleView setRightTextColor(int color) {
        tv_right.setTextColor(color);
        return this;
    }

    public ErBaseTitleView setBackListener(OnClickListener clickListener) {
        if (null != clickListener && null != erIlBack) {
            erIlBack.setOnClickListener(clickListener);
        }
        return this;
    }
    public ErBaseTitleView setRightListener(OnClickListener clickListener) {
        if (null != tv_right) {
            tv_right.setOnClickListener(clickListener);
        }
        return this;
    }

}
