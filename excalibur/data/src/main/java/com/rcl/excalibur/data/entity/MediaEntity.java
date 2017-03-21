package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = MediaEntity.TABLE_NAME)
public class MediaEntity extends Model {

    public static final String TABLE_NAME = "media";


    public MediaEntity() {
        super();
    }

    public List<MediaEntity> getValues() {
        return getMany(MediaEntity.class, MediaValueEntity.COLUMN_MEDIA);
    }

}
