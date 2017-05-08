package com.rcl.excalibur.data.service.response;


public class HistoryLocationResponse {
    private ShipLocationStatsResponse locationStats;
    private ShipLocationResponse location;

    public ShipLocationStatsResponse getLocationStats() {
        return locationStats;
    }

    public void setLocationStats(ShipLocationStatsResponse locationStats) {
        this.locationStats = locationStats;
    }

    public ShipLocationResponse getLocation() {
        return location;
    }

    public void setLocation(ShipLocationResponse location) {
        this.location = location;
    }
}
