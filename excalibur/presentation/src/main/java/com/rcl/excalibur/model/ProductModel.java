package com.rcl.excalibur.model;


import android.content.res.Resources;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.viewtype.ProductInformationViewType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.rcl.excalibur.utils.DateUtils.MINUTES_IN_HOUR;

public class ProductModel extends ProductInformationViewType {
    //TODO Add all your attributes here

    private HashMap<String, String> advisementsAndReestrictions = new LinkedHashMap<>();
    private List<ProductAccessibilityModel> accessibilities = new ArrayList<ProductAccessibilityModel>();
    private String description;
    private int duration;
    private String reservationInformation;
    private String experience;

    public List<ProductAccessibilityModel> getAccessibilities() {
        return accessibilities;
    }

    public HashMap<String, String> getAdvisementsAndReestrictions() {
        return advisementsAndReestrictions;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReservationInformation() {
        return reservationInformation;
    }

    public void setReservationInformation(String reservationInformation) {
        this.reservationInformation = reservationInformation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
