package com.rcl.excalibur.domain;


public class ProductAdvisement {

    private String advisementId;
    private String advisementName;
    private String advisementType;
    private String advisementTitle;
    private String advisementDescription;
    private Media advisementMedia;

    public String getAdvisementId() {
        return advisementId;
    }

    public void setAdvisementId(String advisementId) {
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

    public Media getAdvisementMedia() {
        return advisementMedia;
    }

    public void setAdvisementMedia(Media advisementMedia) {
        this.advisementMedia = advisementMedia;
    }
}
