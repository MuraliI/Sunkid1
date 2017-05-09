package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = ShipLocationStatsEntity.TABLE_NAME)
public class ShipLocationStatsEntity extends Model {
    public static final String TABLE_NAME = "ship_location_stats";
    private static final String COLUMN_NAV_STATUS = "navigational_status";
    private static final String COLUMN_SPEED = "speed";
    private static final String COLUMN_HEADING = "heading";
    private static final String COLUMN_COURSE = "course";

    @Column(name = COLUMN_NAV_STATUS)
    private int aisNavigationalStatus;
    @Column(name = COLUMN_SPEED)
    private int speed;
    @Column(name = COLUMN_HEADING)
    private int heading;
    @Column(name = COLUMN_COURSE)
    private int course;

    public int getAisNavigationalStatus() {
        return aisNavigationalStatus;
    }

    public void setAisNavigationalStatus(int aisNavigationalStatus) {
        this.aisNavigationalStatus = aisNavigationalStatus;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
