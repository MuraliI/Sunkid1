package com.rcl.excalibur.data.service.response;


import java.util.List;

public class ShipStatsResponse {
    private String shipName;
    private String nextPortName;
    private long nextPortArrivalTime;
    private int distanceToTheNextPort;
    private String currentPortName;
    private String lastPortName;
    private long lastPortDepartureTime;
    private ShipLocationResponse shipLocation;
    private ShipLocationStatsResponse shipLocationStats;
    private List<HistoryLocationResponse> historicalLocations;
    private List<FutureLocation> futureLocations;

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getNextPortName() {
        return nextPortName;
    }

    public void setNextPortName(String nextPortName) {
        this.nextPortName = nextPortName;
    }

    public long getNextPortArrivalTime() {
        return nextPortArrivalTime;
    }

    public void setNextPortArrivalTime(long nextPortArrivalTime) {
        this.nextPortArrivalTime = nextPortArrivalTime;
    }

    public int getDistanceToTheNextPort() {
        return distanceToTheNextPort;
    }

    public void setDistanceToTheNextPort(int distanceToTheNextPort) {
        this.distanceToTheNextPort = distanceToTheNextPort;
    }

    public String getCurrentPortName() {
        return currentPortName;
    }

    public void setCurrentPortName(String currentPortName) {
        this.currentPortName = currentPortName;
    }

    public String getLastPortName() {
        return lastPortName;
    }

    public void setLastPortName(String lastPortName) {
        this.lastPortName = lastPortName;
    }

    public long getLastPortDepartureTime() {
        return lastPortDepartureTime;
    }

    public void setLastPortDepartureTime(long lastPortDepartureTime) {
        this.lastPortDepartureTime = lastPortDepartureTime;
    }

    public ShipLocationResponse getShipLocation() {
        return shipLocation;
    }

    public void setShipLocation(ShipLocationResponse shipLocation) {
        this.shipLocation = shipLocation;
    }

    public ShipLocationStatsResponse getShipLocationStats() {
        return shipLocationStats;
    }

    public void setShipLocationStats(ShipLocationStatsResponse shipLocationStats) {
        this.shipLocationStats = shipLocationStats;
    }

    public List<HistoryLocationResponse> getHistoricalLocations() {
        return historicalLocations;
    }

    public void setHistoricalLocations(List<HistoryLocationResponse> historicalLocations) {
        this.historicalLocations = historicalLocations;
    }

    public List<FutureLocation> getFutureLocations() {
        return futureLocations;
    }

    public void setFutureLocations(List<FutureLocation> futureLocations) {
        this.futureLocations = futureLocations;
    }
}
