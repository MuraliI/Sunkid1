package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = OperationHourEntity.TABLE_NAME)
public class OperationHourEntity extends Model {

    public static final String TABLE_NAME = "operation_hour";
    public static final String COLUMN_DAY_NUMBER = "day_number";
    public static final String COLUMN_TIME_OF_DAY = "time_of_day";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_END_TIME = "end_time";
    public static final String COLUMN_LOCATION = "location";

    @Column(name = COLUMN_DAY_NUMBER)
    private String dayNumber;
    @Column(name = COLUMN_TIME_OF_DAY)
    private String timeOfDay;
    @Column(name = COLUMN_START_TIME)
    private String startTime;
    @Column(name = COLUMN_END_TIME)
    private String endTime;
    @Column(name = COLUMN_LOCATION)
    private LocationEntity locationEntity;

    public OperationHourEntity() {
        super();
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public LocationEntity getLocationEntity() {
        return locationEntity;
    }

    public void setLocationEntity(LocationEntity locationEntity) {
        this.locationEntity = locationEntity;
    }
}
