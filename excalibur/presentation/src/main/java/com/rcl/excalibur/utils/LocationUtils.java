package com.rcl.excalibur.utils;

import com.rcl.excalibur.domain.LocationDeckInfo;
import com.rcl.excalibur.domain.ProductLocation;
import com.rcl.excalibur.domain.utils.ConstantsUtil;

import java.util.List;


public final class LocationUtils {

    private LocationUtils() {

    }

    public static String getProductLocation(ProductLocation productLocation) {
        if (productLocation != null) {
            List<LocationDeckInfo> deckInfoList = productLocation.getDeckInfo();
            if (deckInfoList != null && !deckInfoList.isEmpty()) {
                // FIXME: We are using a provisional item, in the next Spring this logic should be fixed
                return String.valueOf(deckInfoList.get(0).getDeckNumber())
                        + ConstantsUtil.WHITE_SPACE + deckInfoList.get(0).getDirection();
            }
        }
        return ConstantsUtil.EMPTY;
    }

    public static String getProductVenue(ProductLocation productLocation) {
        if (productLocation != null) {
            return productLocation.getLocationTitle();
        }
        return ConstantsUtil.EMPTY;
    }

}
