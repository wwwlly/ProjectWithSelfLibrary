package com.kemp.kemplibrary.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import android.widget.TextView;

/**
 * Created by wangkp on 2018/3/9.
 */

public final class ViewUtils {

    public static Drawable getDrawable(Context context, @DrawableRes int id) {
        Drawable d = context.getResources().getDrawable(id);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        return d;
    }

    public static void setDrawableLeft(TextView tv, Drawable d) {
        tv.setCompoundDrawables(d, null, null, null);
    }

    public static void setDrawableTop(TextView tv, Drawable d) {
        tv.setCompoundDrawables(null, d, null, null);
    }

    public static void setDrawableRight(TextView tv, Drawable d) {
        tv.setCompoundDrawables(null, null, d, null);
    }

    public static void setDrawableBottom(TextView tv, Drawable d) {
        tv.setCompoundDrawables(null, null, null, d);
    }
}
