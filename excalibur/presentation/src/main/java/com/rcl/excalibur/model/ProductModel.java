package com.rcl.excalibur.model;


import android.content.res.Resources;

import com.rcl.excalibur.R;

import java.util.LinkedHashMap;

public class ProductModel {
    //TODO Add all your attributes here

    private LinkedHashMap<String, String> advisements = new LinkedHashMap<>();
    private String productId;
    private int duration;

    public LinkedHashMap<String, String> getAdvisements() {
        return advisements;
    }

    public void setAdvisements(LinkedHashMap<String, String> advisements) {
        this.advisements = advisements;
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
        final int minutesInHour = 60;
        int hours = duration / minutesInHour;
        int remainingMinutes = duration % minutesInHour;
        if (hours > 0) {
            durationStr += resources.getQuantityString(R.plurals.product_hr, hours, hours);
        }
        if (remainingMinutes > 0) {
            durationStr += resources.getQuantityString(R.plurals.product_min, remainingMinutes, remainingMinutes);
        }

        return durationStr;
    }
}
