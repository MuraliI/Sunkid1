package com.rcl.excalibur.model;


import android.content.res.Resources;

import com.rcl.excalibur.R;

public class ProductModel {
    //TODO Add all your attributes here

    private int duration;


    //Just for Calculation
    private int minutesInHour = 60;

    public int getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = (int) duration;
    }

    public String getDurationFormatted(Resources resources) {

        String durationStr = "";
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
