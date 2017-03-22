package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = LocationEntity.TABLE_NAME)
public class LocationEntity extends Model {

    public static final String TABLE_NAME = "location";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_HOURS_START = "hours_start";
    public static final String COLUMN_HOURS_END = "hours_end";

    @Column(name = COLUMN_CODE)
    private String code;
    @Column(name = COLUMN_TYPE)
    private String type;
    @Column(name = COLUMN_HOURS_START)
    private String hoursStart;
    @Column(name = COLUMN_HOURS_END)
    private String hoursEnd;

    public LocationEntity() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHoursStart() {
        return hoursStart;
    }

    public void setHoursStart(String hoursStart) {
        this.hoursStart = hoursStart;
    }

    public String getHoursEnd() {
        return hoursEnd;
    }

    public void setHoursEnd(String hoursEnd) {
        this.hoursEnd = hoursEnd;
    }
}
