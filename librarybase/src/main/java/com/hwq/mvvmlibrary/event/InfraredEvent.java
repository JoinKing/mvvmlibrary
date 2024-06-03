package com.hwq.mvvmlibrary.event;

/**
 * A simple {@link InfraredEvent} subclass.
 * Created by WenqiangHuang
 * Creation time 2019/11/27
 * Email vieqqw@163.com
 */
public class InfraredEvent {
    public int code;
    public String data;

    public InfraredEvent(int code, String data) {
        this.code = code;
        this.data = data;
    }
}
