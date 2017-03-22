package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = DurationEntity.TABLE_NAME)
public class DurationEntity extends Model {

    public static final String TABLE_NAME = "duration";
    public static final String COLUMN_AT_YOUR_LEISURE = "at_your_leisure";
    public static final String COLUMN_DURATION_IN_MINUTES = "duration_in_minutes";
    public static final String COLUMN_LEAD_TIME_IN_MINUTES = "lead_time_in_minutes";
    public static final String COLUMN_LAG_TIME_IN_MINUTES = "lag_time_in_minutes";

    @Column(name = COLUMN_AT_YOUR_LEISURE)
    private boolean atYourLeisure;
    @Column(name = COLUMN_DURATION_IN_MINUTES)
    private long durationInMinutes;
    @Column(name = COLUMN_LEAD_TIME_IN_MINUTES)
    private long leadTimeInMinutes;
    @Column(name = COLUMN_LAG_TIME_IN_MINUTES)
    private long lagTimeInMinutes;

    public DurationEntity() {
        super();
    }

    public boolean isAtYourLeisure() {
        return atYourLeisure;
    }

    public void setAtYourLeisure(boolean atYourLeisure) {
        this.atYourLeisure = atYourLeisure;
    }

    public long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(long durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public long getLeadTimeInMinutes() {
        return leadTimeInMinutes;
    }

    public void setLeadTimeInMinutes(long leadTimeInMinutes) {
        this.leadTimeInMinutes = leadTimeInMinutes;
    }

    public long getLagTimeInMinutes() {
        return lagTimeInMinutes;
    }

    public void setLagTimeInMinutes(long lagTimeInMinutes) {
        this.lagTimeInMinutes = lagTimeInMinutes;
    }
}
