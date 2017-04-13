package com.rcl.excalibur.data.utils;

public final class CategoryUtil {

    //TODO Move all logics asociated to Categories to this Class

    public static final String SHOREX = "SHOREX";
    public static final String ACTIVITIES = "ACTIVITIES";
    public static final String ENTERTAINMENT = "ENTERTAINMENT";
    public static final String DINING = "DINING";
    public static final String SPA = "SPA";
    public static final String SHOPPING = "SHOPPING";
    public static final String GUEST_SERVICES = "GUEST_SERVICES";

    private CategoryUtil() {
    }

    public static boolean isShopping(String productType) {
        return SHOPPING.equals(productType);
    }

    public static boolean isActivities(String productType) {
        return ACTIVITIES.equals(productType);
    }

    public static boolean isShorex(String productType) {
        return SHOREX.equals(productType);
    }

    public static boolean isEntertainment(String productType) {
        return ENTERTAINMENT.equals(productType);
    }

    public static boolean isDining(String productType) {
        return DINING.equals(productType);
    }

    public static boolean isSpa(String productType) {
        return SPA.equals(productType);
    }

    public static boolean isGuestServices(String productType) {
        return GUEST_SERVICES.equals(productType);
    }

}
