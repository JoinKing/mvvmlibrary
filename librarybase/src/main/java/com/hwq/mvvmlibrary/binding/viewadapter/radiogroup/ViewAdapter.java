package com.hwq.mvvmlibrary.binding.viewadapter.radiogroup;

import androidx.databinding.BindingAdapter;
import androidx.annotation.IdRes;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hwq.mvvmlibrary.binding.command.BindingCommand;


/**
 * Created by king on 2018.12.21
 */
public class ViewAdapter {
    @BindingAdapter(value = {"onCheckedChangedCommand"}, requireAll = false)
    public static void onCheckedChangedCommand(final RadioGroup radioGroup, final BindingCommand<String> bindingCommand) {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                bindingCommand.execute(radioButton.getText().toString());
            }
        });
    }
}
