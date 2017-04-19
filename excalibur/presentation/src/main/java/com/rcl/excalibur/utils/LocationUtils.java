package com.rcl.excalibur.utils;

import com.rcl.excalibur.domain.ProductLocation;
import com.rcl.excalibur.domain.utils.ConstantsUtil;


public final class LocationUtils {

    private LocationUtils() {

    }

    public static String getProductLocation(ProductLocation productLocation) {
        if (productLocation != null) {
            return String.valueOf(productLocation.getLocationDeckNumber())
                    + ConstantsUtil.WHITE_SPACE + productLocation.getLocationDirection();
        } else {
            return "";
        }
    }

    public static String getProductVenue(ProductLocation productLocation) {
        if (productLocation != null) {
            return productLocation.getLocationVenue();
        } else {
            return "";
        }
    }

}
