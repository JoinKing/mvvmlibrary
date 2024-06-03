package com.hwq.mvvmlibrary.widget.title;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;


/**
 * A 暂无数据 {@link NoDataViewModel} subclass.
 * Created : by WenqiangHuang
 * Create Time: 2020-12-16 14:05
 * Email   :vieqqw@163.com
 */

public class NoDataViewModel extends AndroidViewModel {

    public ObservableField<String> titleText = new ObservableField<>("");

    public NoDataViewModel(@NonNull Application application) {
        super(application);
    }

}