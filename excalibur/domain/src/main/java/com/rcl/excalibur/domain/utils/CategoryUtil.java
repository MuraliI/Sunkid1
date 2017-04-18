package com.rcl.excalibur.domain.utils;

import static com.rcl.excalibur.domain.ProductType.ACTIVITIES_TYPE;
import static com.rcl.excalibur.domain.ProductType.DINING_TYPE;
import static com.rcl.excalibur.domain.ProductType.ENTERTAINMENT_TYPE;
import static com.rcl.excalibur.domain.ProductType.GUEST_SERVICES_TYPE;
import static com.rcl.excalibur.domain.ProductType.SHOPPING_TYPE;
import static com.rcl.excalibur.domain.ProductType.SHOREX_TYPE;
import static com.rcl.excalibur.domain.ProductType.SPA_TYPE;

public final class CategoryUtil {

    //TODO Move all logics asociated to Categories to this Class

    private CategoryUtil() {
    }

    public static boolean isShopping(String productType) {
        return SHOPPING_TYPE.equals(productType);
    }

    public static boolean isActivities(String productType) {
        return ACTIVITIES_TYPE.equals(productType);
    }

    public static boolean isShorex(String productType) {
        return SHOREX_TYPE.equals(productType);
    }

    public static boolean isEntertainment(String productType) {
        return ENTERTAINMENT_TYPE.equals(productType);
    }

    public static boolean isDining(String productType) {
        return DINING_TYPE.equals(productType);
    }

    public static boolean isSpa(String productType) {
        return SPA_TYPE.equals(productType);
    }

    public static boolean isGuestServices(String productType) {
        return GUEST_SERVICES_TYPE.equals(productType);
    }

}
