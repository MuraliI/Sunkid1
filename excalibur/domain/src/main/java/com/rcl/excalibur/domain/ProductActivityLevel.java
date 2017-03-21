package com.rcl.excalibur.domain;



public class ProductActivityLevel {
    private String activityLevelID;
    private String activityLevelTitle;
    private String activityLevelDescription;
    private Media activityLevelMedia;

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

    public Media getActivityLevelMedia() {
        return activityLevelMedia;
    }

    public void setActivityLevelMedia(Media activityLevelMedia) {
        this.activityLevelMedia = activityLevelMedia;
    }
}
