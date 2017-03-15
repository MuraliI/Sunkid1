package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = TimeEntity.TABLE_NAME)
public class TimeEntity extends Model {

    public static final String TABLE_NAME = "times";
    public static final String COLUMN_STANDARD_TIME = "standard_time";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_TIME = "time";

    @Column(name = COLUMN_STANDARD_TIME)
    public StandardTimeEntity standardTimeEntity;
    @Column(name = COLUMN_DAY)
    public String day;
    @Column(name = COLUMN_TIME)
    public String time;

    public TimeEntity() {
        super();
    }

    public StandardTimeEntity getStandardTimeEntity() {
        return standardTimeEntity;
    }

    public void setStandardTimeEntity(StandardTimeEntity standardTimeEntity) {
        this.standardTimeEntity = standardTimeEntity;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
