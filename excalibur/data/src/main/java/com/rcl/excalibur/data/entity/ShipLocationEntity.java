package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = ShipLocationEntity.TABLE_NAME)
public class ShipLocationEntity extends Model {
    public static final String TABLE_NAME = "ship_location";
    private static final String COLUMN_LATITUDE = "ship_latitude";
    private static final String COLUMN_LONGITUDE = "ship_longitude";
    private static final String COLUMN_TIMESTAMP = "ship_timestamp";

    @Column(name = COLUMN_LATITUDE)
    private double latitude;
    @Column(name = COLUMN_LONGITUDE)
    private double longitude;
    @Column(name = COLUMN_TIMESTAMP)
    private long locationTimestamp;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getLocationTimestamp() {
        return locationTimestamp;
    }

    public void setLocationTimestamp(long locationTimestamp) {
        this.locationTimestamp = locationTimestamp;
    }
}
