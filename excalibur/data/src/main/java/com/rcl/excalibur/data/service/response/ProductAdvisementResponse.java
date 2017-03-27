package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

public class ProductAdvisementResponse {
    @SerializedName("advisementID")
    private long advisementId;
    private String advisementName;
    private String advisementType;
    private String advisementTitle;
    private String advisementDescription;
    private MediaResponse advisementMedia;

    public long getAdvisementId() {
        return advisementId;
    }

    public void setAdvisementId(long advisementId) {
        this.advisementId = advisementId;
    }

    public String getAdvisementName() {
        return advisementName;
    }

    public void setAdvisementName(String advisementName) {
        this.advisementName = advisementName;
    }

    public String getAdvisementType() {
        return advisementType;
    }

    public void setAdvisementType(String advisementType) {
        this.advisementType = advisementType;
    }

    public String getAdvisementTitle() {
        return advisementTitle;
    }

    public void setAdvisementTitle(String advisementTitle) {
        this.advisementTitle = advisementTitle;
    }

    public String getAdvisementDescription() {
        return advisementDescription;
    }

    public void setAdvisementDescription(String advisementDescription) {
        this.advisementDescription = advisementDescription;
    }

    public MediaResponse getAdvisementMedia() {
        return advisementMedia;
    }

    public void setAdvisementMedia(MediaResponse advisementMedia) {
        this.advisementMedia = advisementMedia;
    }
}
