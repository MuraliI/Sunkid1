package com.rcl.excalibur.utils;


import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public final class ViewUtils {
    private ViewUtils() {

    }

    @Nullable
    public static View getToolbarTitle(Toolbar toolbar) {
        int childCount = toolbar.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = toolbar.getChildAt(i);
            if (child instanceof TextView) {
                return child;
            }
        }
        return null;
    }
}
