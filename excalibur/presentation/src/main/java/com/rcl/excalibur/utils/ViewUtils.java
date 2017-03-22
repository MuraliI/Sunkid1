package com.rcl.excalibur.utils;


import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;

public final class ViewUtils {
    private ViewUtils() {

    }

    public static int getColor(@ColorRes int colorResource, @NonNull Context context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            return context.getResources().getColor(colorResource, context.getTheme());
        } else {
            return context.getResources().getColor(colorResource);
        }
    }
}
