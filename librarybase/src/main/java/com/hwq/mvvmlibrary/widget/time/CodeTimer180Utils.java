package com.hwq.mvvmlibrary.widget.time;

/**
 * A 验证码倒计时 {@link CodeTimer180Utils} subclass.
 * Created : by WenqiangHuang
 * Create Time: 2020-12-12 21:10
 * Email   :vieqqw@163.com
 */
public class CodeTimer180Utils {

    private long millisInFuture = 180;

    private CodeTimer180Utils() {
        countDownTimer = new CountDownTimer(millisInFuture*1000,1000);
        countDownTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                if (onCountDownTimerListener!=null){
                    onCountDownTimerListener.onTick(millisUntilFinished);
                }

                time = (int) (millisUntilFinished/1000);


            }

            @Override
            public void onFinish() {
                if (onCountDownTimerListener!=null){
                    onCountDownTimerListener.onFinish();
                }

                time = 0;

            }

            @Override
            public void onCancel() {
                if (onCountDownTimerListener!=null){
                    onCountDownTimerListener.onCancel();
                }

                time = 0;

            }
        });
    }
    private int time = 0;
    public int getTime(){

        return time;

    }

    public void star(){
        countDownTimer.start();
    }





    private CountDownTimer countDownTimer;



    private static CodeTimer180Utils instance = new CodeTimer180Utils();


    public static synchronized CodeTimer180Utils getInstance(){
        return instance;
    }


    private OnCountDownTimerListener onCountDownTimerListener;

    public void setOnCountDownTimerListener(OnCountDownTimerListener onCountDownTimerListener) {
        this.onCountDownTimerListener = onCountDownTimerListener;
    }


    public void onDistory(){
        if (instance!=null){
            countDownTimer = null;
            instance = null;
        }
    }
}
