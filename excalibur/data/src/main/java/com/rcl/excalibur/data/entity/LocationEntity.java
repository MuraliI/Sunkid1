package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = LocationEntity.TABLE_NAME)
public class LocationEntity extends Model {

    public static final String TABLE_NAME = "location";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";

    @Column(name = COLUMN_CODE)
    private String code;
    @Column(name = COLUMN_TITLE)
    private String title;
    @Column(name = COLUMN_TYPE)
    private String type;
    @Column(name = COLUMN_LATITUDE)
    private String latitude;
    @Column(name = COLUMN_LONGITUDE)
    private String longitude;

    public LocationEntity() {
        super();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<DeckInfoEntity> getDeckInfo() {
        return getMany(DeckInfoEntity.class, DeckInfoEntity.COLUMN_LOCATION);
    }

    public List<OperationHourEntity> getOperationHours() {
        return getMany(OperationHourEntity.class, OperationHourEntity.COLUMN_LOCATION);
    }
}
