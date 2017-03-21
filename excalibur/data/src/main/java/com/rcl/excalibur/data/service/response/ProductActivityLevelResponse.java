package com.rcl.excalibur.data.service.response;


public class ProductActivityLevelResponse {
    private String activityLevelID;
    private String activityLevelTitle;
    private String activityLevelDescription;
    private MediaResponse activityLevelMedia;

    public String getActivityLevelID() {
        return activityLevelID;
    }

    public void setActivityLevelID(String activityLevelID) {
        this.activityLevelID = activityLevelID;
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
