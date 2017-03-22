package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

public class PromotionMessageResponse {

    private String messageTitle;

    private String messageDescription;

    @SerializedName("categoryID")
    private String categoryId;

    private String locationCode;

    @SerializedName("productID")
    private String [] productId;

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String[] getProductId() {
        return productId;
    }

    public void setProductId(String[] productId) {
        this.productId = productId;
    }
}
