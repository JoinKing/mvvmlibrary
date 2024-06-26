package com.hwq.mvvmlibrary.widget.refreshlayout.processor;

import android.view.MotionEvent;

/**
 * Created by king on 2019/1/7.
 */

public interface IDecorator {
    boolean dispatchTouchEvent(MotionEvent ev);

    boolean interceptTouchEvent(MotionEvent ev);

    boolean dealTouchEvent(MotionEvent ev);

    void onFingerDown(MotionEvent ev);

    void onFingerUp(MotionEvent ev, boolean isFling);

    void onFingerScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY, float velocityX, float velocityY);

    void onFingerFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
}
