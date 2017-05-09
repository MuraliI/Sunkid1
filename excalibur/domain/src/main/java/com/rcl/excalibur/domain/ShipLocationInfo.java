package com.rcl.excalibur.domain;


public class ShipLocationInfo {
    private double latitude;
    private double longitude;
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
