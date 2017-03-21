package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

public class ProductActivityLevelResponse {
    @SerializedName("activityLevelID")
    private String activityLevelId;
    private String activityLevelTitle;
    private String activityLevelDescription;
    private MediaResponse activityLevelMedia;

    public String getActivityLevelId() {
        return activityLevelId;
    }

    public void setActivityLevelId(String activityLevelId) {
        this.activityLevelId = activityLevelId;
    }

    public String getActivityLevelTitle() {
        return activityLevelTitle;
    }

    public void setActivityLevelTitle(String activityLevelTitle) {
        this.activityLevelTitle = activityLevelTitle;
    }

    public String getActivityLevelDescription() {
        return activityLevelDescription;
    }

    public void setActivityLevelDescription(String activityLevelDescription) {
        this.activityLevelDescription = activityLevelDescription;
    }

    public MediaResponse getActivityLevelMedia() {
        return activityLevelMedia;
    }

    public void setActivityLevelMedia(MediaResponse activityLevelMedia) {
        this.activityLevelMedia = activityLevelMedia;
    }
}
