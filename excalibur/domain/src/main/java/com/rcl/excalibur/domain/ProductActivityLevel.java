package com.rcl.excalibur.domain;


public class ProductActivityLevel {

    private String activityLevelId;
    private String activityLevelTitle;
    private String activityLevelDescription;
    private Media activityLevelMedia;

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

    public Media getActivityLevelMedia() {
        return activityLevelMedia;
    }

    public void setActivityLevelMedia(Media activityLevelMedia) {
        this.activityLevelMedia = activityLevelMedia;
    }
}
