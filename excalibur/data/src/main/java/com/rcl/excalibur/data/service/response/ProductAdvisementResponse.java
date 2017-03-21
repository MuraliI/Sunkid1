package com.rcl.excalibur.data.service.response;



public class ProductAdvisementResponse {
    private String advisementID;
    private String advisementName;
    private String advisementType;
    private String advisementTitle;
    private String advisementDescription;
    private MediaResponse advisementMedia;

    public String getAdvisementID() {
        return advisementID;
    }

    public void setAdvisementID(String advisementID) {
        this.advisementID = advisementID;
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
