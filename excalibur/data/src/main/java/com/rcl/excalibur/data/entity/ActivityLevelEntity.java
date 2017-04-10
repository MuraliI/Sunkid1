package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = ActivityLevelEntity.TABLE_NAME)
public class ActivityLevelEntity extends Model {

    public static final String TABLE_NAME = "activity_level";
    public static final String COLUMN_ACTIVITY_LEVEL_ID = "activity_level_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_MEDIA = "media";

    //TODO create unique constraints and indexes when services are defined
    @Column(name = COLUMN_ACTIVITY_LEVEL_ID)
    private String activityLevelId;
    @Column(name = COLUMN_TITLE)
    private String title;
    @Column(name = COLUMN_DESCRIPTION)
    private String description;
    @Column(name = COLUMN_MEDIA)
    private MediaEntity media;

    public ActivityLevelEntity() {
        super();
    }

    public String getActivityLevelId() {
        return activityLevelId;
    }

    public void setActivityLevelId(String activityLevelId) {
        this.activityLevelId = activityLevelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MediaEntity getMedia() {
        return media;
    }

    public void setMedia(MediaEntity media) {
        this.media = media;
    }
}
