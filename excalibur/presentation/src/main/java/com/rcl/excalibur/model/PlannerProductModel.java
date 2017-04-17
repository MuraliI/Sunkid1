package com.rcl.excalibur.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.viewtype.ProductInformationViewType;
import com.rcl.excalibur.utils.PartOfDayUtils;

import java.util.Calendar;

public class PlannerProductModel extends ProductInformationViewType implements Comparable<PlannerProductModel> {

    // STATE value represent priority in list Higher priority go on top
    public static final int STATE_MORNING = 3;
    public static final int STATE_AFTERNOON = 2;
    public static final int STATE_EVENING = 1;
    public static final int STATE_LATE_NIGHT = 0;


    private boolean isPromoted;
    @DrawableRes
    private int resourceIdCategoryIcon;
    private int priceRange;
    private String operatingHours;
    private String deckAndDirection;
    private Calendar startDate;
    private Calendar endDate;
    private boolean isAllDayProduct;

    public boolean isPromoted() {
        return isPromoted;
    }

    public void setPromoted(boolean promoted) {
        isPromoted = promoted;
    }

    public int getResourceIdCategoryIcon() {
        return resourceIdCategoryIcon;
    }

    public void setResourceIdCategoryIcon(int resourceIdCategoryIcon) {
        this.resourceIdCategoryIcon = resourceIdCategoryIcon;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public String getDeckAndDirection() {
        return deckAndDirection;
    }

    public void setDeckAndDirection(String deckAndDirection) {
        this.deckAndDirection = deckAndDirection;
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

    public boolean hourIsDifferent(PlannerProductModel o) {
        return this.getStartDate().get(Calendar.HOUR) != o.getStartDate().get(Calendar.HOUR);
    }

    public boolean isAllDayProduct() {
        return isAllDayProduct;
    }

    public void setAllDayProduct(boolean allDayProduct) {
        isAllDayProduct = allDayProduct;
    }

    public int getState() {
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
    public int compareTo(@NonNull PlannerProductModel input) {
        if (getState() > input.getState()) {
            return -1;
        } else if (getState() < input.getState()) {
            return 1;
        }

        // Case state equal priority
        if (this.getStartDate().getTime().getTime()
                > input.getStartDate().getTime().getTime()) {
            return 1;
        } else if (this.getStartDate().getTime().getTime()
                < input.getStartDate().getTime().getTime()) {
            return -1;
        } else {
            return this.getProductName().compareTo(input.getProductName());
        }
    }
}


