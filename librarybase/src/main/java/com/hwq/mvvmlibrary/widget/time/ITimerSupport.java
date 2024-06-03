package com.hwq.mvvmlibrary.widget.time;

/**
 * A simple {@link ITimerSupport} subclass.
 * Created : by WenqiangHuang
 * Created : 2020/9/23 17:01
 * Email   :vieqqw@163.com
 */
public interface ITimerSupport {
    void start();

    void pause();

    void resume();

    void stop();

    void reset();
}
