
package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ActivityLevelResponse {

    private String activityLevelDescription;
    @SerializedName("activityLevelID")
    private String activityLevelId;
    private ActivityLevelMedia activityLevelMedia;
    private String activityLevelTitle;

    public ActivityLevelResponse() {
    }

    public String getActivityLevelDescription() {
        return activityLevelDescription;
    }

    public void setActivityLevelDescription(String activityLevelDescription) {
        this.activityLevelDescription = activityLevelDescription;
    }

    public String getActivityLevelId() {
        return activityLevelId;
    }

    public void setActivityLevelId(String activityLevelId) {
        this.activityLevelId = activityLevelId;
    }

    public ActivityLevelMedia getActivityLevelMedia() {
        return activityLevelMedia;
    }

    public void setActivityLevelMedia(ActivityLevelMedia activityLevelMedia) {
        this.activityLevelMedia = activityLevelMedia;
    }

    public String getActivityLevelTitle() {
        return activityLevelTitle;
    }

    public void setActivityLevelTitle(String activityLevelTitle) {
        this.activityLevelTitle = activityLevelTitle;
    }
}
