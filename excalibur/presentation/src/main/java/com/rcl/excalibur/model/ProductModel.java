package com.rcl.excalibur.model;


import android.content.res.Resources;
import com.rcl.excalibur.R;
import java.util.HashMap;
import com.rcl.excalibur.adapters.viewtype.ProductInformationViewType;
import java.util.LinkedHashMap;

import static com.rcl.excalibur.utils.DateUtils.MINUTES_IN_HOUR;

public class ProductModel extends ProductInformationViewType {
    //TODO Add all your attributes here

    private HashMap<String, String> advisementsAndReestrictions = new LinkedHashMap<>();
    private String productId;
    private int duration;
    private String reservationInformation;


    public HashMap<String, String> getAdvisementsAndReestrictions() {
        return advisementsAndReestrictions;
    }

    public void setAdvisementsAndReestrictions(LinkedHashMap<String, String> advisementsAndReestrictions) {
        this.advisementsAndReestrictions = advisementsAndReestrictions;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = (int) duration;
    }

    public String getDurationFormatted(Resources resources) {
        String durationStr = "";
        int hours = duration / MINUTES_IN_HOUR;
        int remainingMinutes = duration % MINUTES_IN_HOUR;
        if (hours > 0) {
            durationStr += resources.getQuantityString(R.plurals.product_hr, hours, hours);
        }
        if (remainingMinutes > 0) {
            durationStr += resources.getQuantityString(R.plurals.product_min, remainingMinutes, remainingMinutes);
        }

        return durationStr;
    }

    public String getReservationInformation() {
        return reservationInformation;
    }

    public void setReservationInformation(String reservationInformation) {
        this.reservationInformation = reservationInformation;
    }
}
