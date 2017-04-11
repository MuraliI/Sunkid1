package com.rcl.excalibur.model.itinerary;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

import java.util.Calendar;

public class ItineraryProductModel implements RecyclerViewType, Comparable<ItineraryProductModel> {

    public static final String LOCATION_POINTER_FWD = "Forward";
    public static final String LOCATION_POINTER_AFT = "After";
    public static final String LOCATION_POINTER_MID = "Middle";


    //STATE value represent priority in list Higher priority go on top
    public static final int STATE_PAST = 3;
    public static final int STATE_ON_GOING = 2;
    public static final int STATE_UP_COMING = 1;

    private boolean isFavorite;
    @DrawableRes
    private int resourceIdIcon;
    private String productId;
    private String imageUrl;
    private String name;
    private String venue;
    private String deckNumber;
    private String locationPointer;
    private String operatingHours;
    private Calendar startDate;
    private Calendar endDate;
    private String deckAndDirection;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @DrawableRes
    public int getResourceIdIcon() {
        return resourceIdIcon;
    }

    public void setResourceIdIcon(@DrawableRes int resourceIdIcon) {
        this.resourceIdIcon = resourceIdIcon;
    }

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

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
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

    public boolean hourIsDifferent(ItineraryProductModel o) {
        return this.getStartDate().get(Calendar.HOUR) != o.getStartDate().get(Calendar.HOUR);
    }

    public int getState() {

        Calendar now = Calendar.getInstance();

        if (compareHourMinute(now, endDate) > 0) {
            return STATE_PAST;
        }
        if (compareHourMinute(now, startDate) >= 0 && compareHourMinute(now, endDate) <= 0) {
            return STATE_ON_GOING;
        }

        return STATE_UP_COMING;
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
    public int compareTo(@NonNull ItineraryProductModel o) {

        if (getState() > o.getState()) {
            return -1;
        } else if (getState() < o.getState()) {
            return 1;
        }

        //case state equal priority
        if (this.getStartDate().getTime().getTime() > o.getStartDate().getTime().getTime())
            return 1;
        else
            return -1;

    }

    public void setDeckAndDirection(String deckAndDirection) {
        this.deckAndDirection = deckAndDirection;
    }

    public String getDeckAndDirection() {
        return deckAndDirection;
    }
}


