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

    public static boolean isShopping(String productCATEGORY) {
        return SHOPPING_CATEGORY.equals(productCATEGORY);
    }

    public static boolean isActivities(String productCATEGORY) {
        return ACTIVITIES_CATEGORY.equals(productCATEGORY);
    }

    public static boolean isShorex(String productCATEGORY) {
        return SHOREX_CATEGORY.equals(productCATEGORY);
    }

    public static boolean isEntertainment(String productCATEGORY) {
        return ENTERTAINMENT_CATEGORY.equals(productCATEGORY);
    }

    public static boolean isDining(String productCATEGORY) {
        return DINING_CATEGORY.equals(productCATEGORY);
    }

    public static boolean isSpa(String productCATEGORY) {
        return SPA_CATEGORY.equals(productCATEGORY);
    }

    public static boolean isGuestServices(String productCATEGORY) {
        return GUEST_SERVICES_CATEGORY.equals(productCATEGORY);
    }

}
