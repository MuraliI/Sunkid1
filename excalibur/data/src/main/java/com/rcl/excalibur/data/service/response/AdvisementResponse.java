
package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

public class AdvisementResponse {

    @SerializedName("advisementID")
    private String advisementId;
    private String advisementName;
    //??@SerializedName("advisementType")
    private Object advisementType;
    private String advisementTitle;
    private String advisementDescription;
    private String advisementMedia;

    public AdvisementResponse() {
    }

    public String getAdvisementDescription() {
        return advisementDescription;
    }

    public void setAdvisementDescription(String advisementDescription) {
        this.advisementDescription = advisementDescription;
    }

    public String getAdvisementID() {
        return advisementId;
    }

    public void setAdvisementID(String advisementID) {
        advisementId = advisementID;
    }

    public String getAdvisementMedia() {
        return advisementMedia;
    }

    public void setAdvisementMedia(String advisementMedia) {
        this.advisementMedia = advisementMedia;
    }

    public String getAdvisementName() {
        return advisementName;
    }

    public void setAdvisementName(String advisementName) {
        this.advisementName = advisementName;
    }

    public String getAdvisementTitle() {
        return advisementTitle;
    }

    public void setAdvisementTitle(String advisementTitle) {
        this.advisementTitle = advisementTitle;
    }

    public Object getAdvisementType() {
        return advisementType;
    }

    public void setAdvisementType(Object advisementType) {
        this.advisementType = advisementType;
    }

}
