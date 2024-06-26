package com.hwq.mvvmlibrary.widget.cookbar;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.AttrRes;

/**
 *
 *  Created by KING on 2019/1/14.
 */
public class ThemeResolver {

  public static int getColor(Context context, @AttrRes int attr) {
    return getColor(context, attr, 0);
  }

  public static int getColor(Context context, @AttrRes int attr, int defaultColor) {
    TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
    try {
      return a.getColor(0, defaultColor);
    } finally {
      a.recycle();
    }
  }

}
