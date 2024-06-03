package com.hwq.mvvmlibrary.widget.time;

/**
 * A simple {@link OnCountDownTimerListener} subclass.
 * Created : by WenqiangHuang
 * Created : 2020/9/23 17:02
 * Email   :vieqqw@163.com
 */
public interface OnCountDownTimerListener {
    /**
     * 间隔的回调
     *
     * @param millisUntilFinished 剩余的时间
     * @see <a href="https://developer.android.com/reference/android/os/CountDownTimer#onTick(long)">CountDownTimer#onTick(long)</a>
     */
    void onTick(long millisUntilFinished);

    /**
     * 倒计时完成后的回调
     *
     * @see <a href="https://developer.android.com/reference/android/os/CountDownTimer#onFinish()">CountDownTimer#onFinish()</a>
     */
    void onFinish();

    /**
     * 手动停止计时器的回调
     */
    void onCancel();
}

