package com.hwq.mvvmlibrary.binding.viewadapter.checkbox;

import androidx.databinding.BindingAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.hwq.mvvmlibrary.binding.command.BindingCommand;


/**
 * Created by king on 2018.12.21
 */

public class ViewAdapter {
    /**
     * @param bindingCommand //绑定监听
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"onCheckedChangedCommand"}, requireAll = false)
    public static void setCheckedChanged(final CheckBox checkBox, final BindingCommand<Boolean> bindingCommand) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bindingCommand.execute(b);
            }
        });
    }
}
