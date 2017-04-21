package com.rcl.excalibur.utils;

import android.support.annotation.DrawableRes;

import com.rcl.excalibur.R;

import java.util.HashMap;

public class CategoryUtils {

    public static final String DINING = "DINING";
    public static final String SHOREX = "SHOREX";
    public static final String SPA = "SPA";
    public static final String SHOP = "SHOPPING";
    public static final String ENTERTAINMENT = "ENTERTAINMENT";
    public static final String ACTIVITIES = "ACTIVITIES";
    public static final String GUEST_SERVICES = "GUEST_SERVICES";

    private static HashMap<String, Integer> iconMap;
    static {
        iconMap = new HashMap<>();
        iconMap.put(DINING, R.drawable.icon_dining_color);
        iconMap.put(SHOREX, R.drawable.icon_shorex_color);
        iconMap.put(SPA, R.drawable.icon_spa_color);
        iconMap.put(SHOP, R.drawable.icon_shops_color);
        iconMap.put(ENTERTAINMENT, R.drawable.icon_entertainment_color);
        iconMap.put(ACTIVITIES, R.drawable.icon_services_color);
        iconMap.put(GUEST_SERVICES, R.drawable.icon_services_color);
    }

    private CategoryUtils() {
        // Do Nothing
    }

    @DrawableRes
    public static Integer getCategoryIcon(String productType) {
        return iconMap.get(productType);
    }
}
