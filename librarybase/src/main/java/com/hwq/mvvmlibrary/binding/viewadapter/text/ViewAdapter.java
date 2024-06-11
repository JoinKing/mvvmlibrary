package com.hwq.mvvmlibrary.binding.viewadapter.text;

import android.text.Html;
import android.text.Spanned;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hwq.mvvmlibrary.binding.command.BindingCommand;


/**
 * Created by king on 2018.12.21
 */

public class ViewAdapter {

    @BindingAdapter(value = {"text"}, requireAll = false)
    public static void setText(final TextView textView, String text) {
        if (text != null) {
            textView.setText(text);
        } else {
            textView.setText("");
        }
    }

    @BindingAdapter(value = {"htmlString"}, requireAll = false)
    public static void htmlString(final TextView textView, String htmlString) {
        if (htmlString != null) {
            Spanned spannedText = Html.fromHtml(htmlString, Html.FROM_HTML_MODE_COMPACT);
            textView.setText(spannedText);
        }
    }
}
