package com.hwq.mvvmlibrary.http.utils;


import com.google.gson.Gson;
import com.hwq.mvvmlibrary.http.BaseResponse;
import com.hwq.mvvmlibrary.http.NetworkUtil;
import com.hwq.mvvmlibrary.http.ResponseThrowable;
import com.hwq.mvvmlibrary.utils.ToastUtils;
import com.hwq.mvvmlibrary.utils.Utils;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<T> {


    private static final int SUCCESS = 200;//操作成功


    @Override
    public void onNext(T t) {
        BaseResponse baseResponse = (BaseResponse) t;
        int status = baseResponse.status;
        if (status == SUCCESS) {
            onResult((T) baseResponse);
        } else {
            onError(new ResponseThrowable(new Throwable(baseResponse.msg), String.valueOf(status)));


        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ResponseThrowable) {
            onError((ResponseThrowable) e);
        } else {
            onError(e);
        }
        ToastUtils.showLong(e.getMessage());
    }

    /**
     * 开始加载弹窗
     */
    @Override
    public void onStart() {
        super.onStart();
        if (!NetworkUtil.isNetworkAvailable(Utils.getContext())) {
            ToastUtils.showShort("无网络，读取缓存数据");
            onComplete();
        }

    }

    /**
     * 关闭加载弹窗
     */
    @Override
    public void onComplete() {

    }


    public abstract void onError(ResponseThrowable e);


    public abstract void onResult(T t);

}
