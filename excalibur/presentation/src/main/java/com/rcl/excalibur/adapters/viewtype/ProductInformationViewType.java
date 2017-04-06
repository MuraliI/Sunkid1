package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;


public class ProductInformationViewType implements RecyclerViewType {

    private Long productId;
    private String productName;
    private String productType;
    private String[] productMedia;
    private String venue;
    private String location;
    private String port;
    private int upChargeLevel;
    private boolean isReservationRequired;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String[] getProductMedia() {
        return productMedia;
    }

    public void setProductMedia(String[] productMedia) {
        this.productMedia = productMedia;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getUpChargeLevel() {
        return upChargeLevel;
    }

    public void setUpChargeLevel(int upChargeLevel) {
        this.upChargeLevel = upChargeLevel;
    }

    public boolean isReservationRequired() {
        return isReservationRequired;
    }

    public void setReservationRequired(boolean reservationRequired) {
        isReservationRequired = reservationRequired;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_PRODUCT_BASIC_INFORMATION;
    }
}
