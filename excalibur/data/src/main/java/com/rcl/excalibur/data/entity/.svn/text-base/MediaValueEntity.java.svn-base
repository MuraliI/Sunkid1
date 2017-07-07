package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = MediaValueEntity.TABLE_NAME)
public class MediaValueEntity extends Model {

    public static final String TABLE_NAME = "media_value";
    public static final String COLUMN_MEDIA = "media";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_LINK = "link";

    @Column(name = COLUMN_MEDIA, onDelete = Column.ForeignKeyAction.CASCADE)
    private MediaEntity media;
    @Column(name = COLUMN_TYPE)
    private String type;
    @Column(name = COLUMN_LINK)
    private String link;

    public MediaValueEntity() {
        super();
    }

    public MediaEntity getMedia() {
        return media;
    }

    public void setMedia(MediaEntity media) {
        this.media = media;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
