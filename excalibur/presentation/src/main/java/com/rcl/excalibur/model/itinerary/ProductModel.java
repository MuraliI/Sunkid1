package com.rcl.excalibur.model.itinerary;

import android.support.annotation.NonNull;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

import java.util.Date;

public class ProductModel implements RecyclerViewType, Comparable<ProductModel> {
    public static final String LOCATION_POINTER_FWD = "Forward";
    public static final String LOCATION_POINTER_AFT = "After";
    public static final String LOCATION_POINTER_MID = "Middle";

    private String productId;
    private String imageUrl;
    private String name;
    private String venue;
    private String deckNumber;
    private String locationPointer;
    private String operatinghours;
    private Date startDate;
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean hourIsDifferent(ProductModel o) {
        return this.getStartDate().getHours() != o.getStartDate().getHours();
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_PRODUCT_VIEW;
    }

    @Override
    public int compareTo(@NonNull ProductModel o) {
        if (this.getStartDate().getTime() > o.getStartDate().getTime())
            return 1;
        else
            return -1;
    }
}

