package com.rcl.excalibur.domain;


public class ProductActivityLevel {

    private long activityLevelId;
    private String activityLevelTitle;
    private String activityLevelDescription;
    private Media activityLevelMedia;

    public long getActivityLevelId() {
        return activityLevelId;
    }

    public void setActivityLevelId(long activityLevelId) {
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
