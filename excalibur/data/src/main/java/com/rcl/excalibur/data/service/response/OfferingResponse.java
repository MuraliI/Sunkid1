package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

public class OfferingResponse {

    @SerializedName("productID")
    private String productId;
    @SerializedName("offeringID")
    private String offeringId;
    private String offeringDate;
    private String offeringTime;
    private SellingPriceResponse offeringPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(String id) {
        this.offeringId = id;
    }

    public String getOfferingDate() {
        return offeringDate;
    }

    public void setOfferingDate(String offeringDate) {
        this.offeringDate = offeringDate;
    }

    public String getOfferingTime() {
        return offeringTime;
    }

    public void setOfferingTime(String offeringTime) {
        this.offeringTime = offeringTime;
    }

    public SellingPriceResponse getOfferingPrice() {
        return offeringPrice;
    }

    public void setOfferingPrice(SellingPriceResponse offeringPrice) {
        this.offeringPrice = offeringPrice;
    }
}
