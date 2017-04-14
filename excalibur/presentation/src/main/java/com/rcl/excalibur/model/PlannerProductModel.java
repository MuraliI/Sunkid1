package com.rcl.excalibur.model;

import android.support.annotation.NonNull;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.utils.PartOfDayUtils;

import java.util.Calendar;

public class PlannerProductModel implements RecyclerViewType, Comparable<PlannerProductModel> {

    // STATE value represent priority in list Higher priority go on top
    public static final int STATE_MORNING = 3;
    public static final int STATE_AFTERNOON = 2;
    public static final int STATE_EVENING = 1;
    public static final int STATE_LATE_NIGHT = 0;

    private String productId;
    private String imageUrl;
    private String name;
    private String venue;
    private String deckNumber;
    private String locationPointer;
    private String operatinghours;
    private Calendar startDate;
    private Calendar endDate;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    public String getDeckNumber() {
        return deckNumber;
    }

    public String getLocationPointer() {
        return locationPointer;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setDeckNumber(String deckNumber) {
        this.deckNumber = deckNumber;
    }

    public void setLocationPointer(String locationPointer) {
        this.locationPointer = locationPointer;
    }

    public String getOperatinghours() {
        return operatinghours;
    }

    public void setOperatinghours(String operatinghours) {
        this.operatinghours = operatinghours;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public boolean hourIsDifferent(PlannerProductModel o) {
        return this.getStartDate().get(Calendar.HOUR) != o.getStartDate().get(Calendar.HOUR);
    }

    public int getState() {

        /*Calendar now = Calendar.getInstance();

        if (compareHourMinute(now, endDate) > 0) {
            return STATE_PAST;
        }
        if (compareHourMinute(now, startDate) >= 0 && compareHourMinute(now, endDate) <= 0) {
            return STATE_ON_GOING;
        }

        return STATE_UP_COMING;*/

        return PartOfDayUtils.getPartOfDayState(startDate);
    }

    private int compareHourMinute(Calendar date1, Calendar date2) {

        /*FIXME: This is to user Mockdata with old date values only using Hours & Minutes*/

        if (date1.get(Calendar.HOUR_OF_DAY) > date2.get(Calendar.HOUR_OF_DAY))
            return 1;
        else if (date1.get(Calendar.HOUR_OF_DAY) < date2.get(Calendar.HOUR_OF_DAY))
            return -1;
        else {
            if (date1.get(Calendar.MINUTE) > date2.get(Calendar.MINUTE)) {
                return 1;
            } else if (date1.get(Calendar.MINUTE) < date2.get(Calendar.MINUTE)) {
                return -1;
            }
            return 0;
        }
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_ITINERARY_PRODUCT_VIEW;
    }

    @Override
    public int compareTo(@NonNull PlannerProductModel o) { //TODO: Missing compare by same hour and by name
        if (getState() > o.getState()) {
            return -1;
        } else if (getState() < o.getState()) {
            return 1;
        }

        // Case state equal priority
        if (this.getStartDate().getTime().getTime()
                > o.getStartDate().getTime().getTime()) {
            return 1;
        } else {
            return -1;
        }
    }
}


