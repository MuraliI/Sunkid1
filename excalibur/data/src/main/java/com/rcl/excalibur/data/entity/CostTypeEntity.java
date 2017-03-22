package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = CostTypeEntity.TABLE_NAME)
public class CostTypeEntity extends Model {

    public static final String TABLE_NAME = "cost_type";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_MEDIA = "media";

    @Column(name = COLUMN_CODE)
    private String code;
    @Column(name = COLUMN_TITLE)
    private String title;
    @Column(name = COLUMN_DESCRIPTION)
    private String description;
    @Column(name = COLUMN_MEDIA)
    private MediaEntity media;

    public CostTypeEntity() {
        super();
    }

    public MediaEntity getMedia() {
        return media;
    }

    public void setMedia(MediaEntity media) {
        this.media = media;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
