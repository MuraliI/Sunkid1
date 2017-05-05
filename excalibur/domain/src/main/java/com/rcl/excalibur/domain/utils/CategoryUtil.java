package com.rcl.excalibur.domain.utils;

import static com.rcl.excalibur.domain.Category.ACTIVITIES_CATEGORY;
import static com.rcl.excalibur.domain.Category.DINING_CATEGORY;
import static com.rcl.excalibur.domain.Category.ENTERTAINMENT_CATEGORY;
import static com.rcl.excalibur.domain.Category.GUEST_SERVICES_CATEGORY;
import static com.rcl.excalibur.domain.Category.SHOPPING_CATEGORY;
import static com.rcl.excalibur.domain.Category.SHOREX_CATEGORY;
import static com.rcl.excalibur.domain.Category.SPA_CATEGORY;

public final class CategoryUtil {

    //TODO Move all logics asociated to Categories to this Class

    private CategoryUtil() {
    }

    public static boolean isShopping(String productCategory) {
        return SHOPPING_CATEGORY.equals(productCategory);
    }

    public static boolean isActivities(String productCategory) {
        return ACTIVITIES_CATEGORY.equals(productCategory);
    }

    public static boolean isShorex(String productCategory) {
        return SHOREX_CATEGORY.equals(productCategory);
    }

    public static boolean isEntertainment(String productCategory) {
        return ENTERTAINMENT_CATEGORY.equals(productCategory);
    }

    public static boolean isDining(String productCategory) {
        return DINING_CATEGORY.equals(productCategory);
    }

    public static boolean isSpa(String productCategory) {
        return SPA_CATEGORY.equals(productCategory);
    }

    public static boolean isGuestServices(String productCategory) {
        return GUEST_SERVICES_CATEGORY.equals(productCategory);
    }

}
