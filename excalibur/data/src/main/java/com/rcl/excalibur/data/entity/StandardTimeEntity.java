package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = StandardTimeEntity.TABLE_NAME)
public class StandardTimeEntity extends Model {

    public static final String TABLE_NAME = "standard_time";
    public static final String COLUMN_TITLE = "title";

    @Column(name = COLUMN_TITLE)
    public String title;

    public StandardTimeEntity() {
        super();
    }

    public List<TimeEntity> getTimes() {
        return getMany(TimeEntity.class, TimeEntity.COLUMN_STANDARD_TIME);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
