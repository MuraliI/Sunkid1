package com.rcl.excalibur.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.viewtype.ProductInformationViewType;
import com.rcl.excalibur.utils.PartOfDayUtils;

import java.util.Calendar;

public class PlannerProductModel extends ProductInformationViewType implements Comparable<PlannerProductModel> {

    public static final int STATE_ALL_DAY = 4;
    public static final int STATE_MORNING = 3;
    public static final int STATE_AFTERNOON = 2;
    public static final int STATE_EVENING = 1;
    public static final int STATE_LATE_NIGHT = 0;

    @DrawableRes
    private int resourceIdCategoryIcon;
    private String operatingHours;
    private Calendar startDate;
    private Calendar endDate;
    private boolean isAllDayProduct;

    @DrawableRes
    public int getResourceIdCategoryIcon() {
        return resourceIdCategoryIcon;
    }

    public void setResourceIdCategoryIcon(int resourceIdCategoryIcon) {
        this.resourceIdCategoryIcon = resourceIdCategoryIcon;
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

    public boolean isAllDayProduct() {
        return isAllDayProduct;
    }

    public void setAllDayProduct(boolean allDayProduct) {
        isAllDayProduct = allDayProduct;
    }

    public int getState() {
        return isAllDayProduct ? STATE_ALL_DAY : PartOfDayUtils.getPartOfDayState(startDate);
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_ITINERARY_PRODUCT_VIEW;
    }

    @Override
    public int compareTo(@NonNull PlannerProductModel input) {
        return getStartDate().compareTo(input.getStartDate());
    }
}


