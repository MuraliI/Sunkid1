package com.rcl.excalibur.utils;

import android.support.annotation.DrawableRes;

import com.rcl.excalibur.R;

public class CategoryUtils {

    public static final String ACTIVITIES = "ACTIVITIES";
    public static final String DINING = "DINING";
    public static final String ENTERTAINMENT = "ENTERTAINMENT";
    public static final String SHOREX = "SHOREX";
    public static final String SPA = "SPA";

    private CategoryUtils() {
        // Noting to do
    }

    @DrawableRes
    public static int getCategoryIcon(String productType) {
        switch (productType) {
            case ACTIVITIES:
                return R.drawable.icon_services_color;
            case DINING:
                return R.drawable.icon_dining_color;
            case ENTERTAINMENT:
                return R.drawable.icon_entertainment_color;
            case SHOREX:
                return R.drawable.icon_shops_color;
            case SPA:
                return R.drawable.icon_spa_color;
            default:
                return R.drawable.icon_excrusions_color;
        }
    }
}
