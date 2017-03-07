package com.rcl.excalibur.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

public class RCViewUtils {
    private RCViewUtils() {
    }

    @Nullable
    public static Drawable tintDrawable(@NonNull Context context, @DrawableRes int drawableRes, @ColorRes int colorRes) {
        Drawable drawable = context.getDrawable(drawableRes);
        int color = ContextCompat.getColor(context, colorRes);
        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            DrawableCompat.setTint(drawable, color);
        }
        return drawable;
    }
}
