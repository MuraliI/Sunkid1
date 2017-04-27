package com.rcl.excalibur.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.rcl.excalibur.adapters.viewtype.ProductInformationViewType;
import com.rcl.excalibur.utils.PartOfDayUtils;

import java.util.Calendar;

public class PlannerProductModel extends ProductInformationViewType implements Comparable<PlannerProductModel> {

    public static final int STATE_ALL_DAY = 4;
    public static final int STATE_MORNING = 3;
    public static final int STATE_AFTERNOON = 2;
    public static final int STATE_EVENING = 1;
    public static final int STATE_LATE_NIGHT = 0;

    public static final int GENERAL_HEADER = 0;
    public static final int ALL_DAY_HEADER = 1;

    @DrawableRes private int resourceIdCategoryIcon;
    private String operatingHours;
    private Calendar startDate;
    private Calendar endDate;
    private boolean isAllDayProduct;
    private boolean isFeatured;
    private boolean isHighlighted;
    private int indexToBeAdded;

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

    public int getHeaderItBelongs() {
        return isAllDayProduct ? ALL_DAY_HEADER : GENERAL_HEADER;
    }

    public int getState() {
        return isAllDayProduct ? STATE_ALL_DAY : PartOfDayUtils.getPartOfDayState(startDate);
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }

    @Override
    public int getViewType() {
        return 0;
    }

    public int getIndexToBeAdded() {
        return indexToBeAdded;
    }

    public void setIndexToBeAdded(int indexToBeAdded) {
        this.indexToBeAdded = indexToBeAdded;
    }

    @Override
    public int compareTo(@NonNull PlannerProductModel input) {
        int result = getStartDate().compareTo(input.getStartDate());
        if (result == 0) {
            result = input.getProductName().compareToIgnoreCase(input.getProductName());
        }
        return result;
    }
}


