package com.rcl.excalibur.data.service.response;


public class ShipStatsResponse {
    private String shipName;
    private String nextPortName;
    private long nextPortArrivalTime;
    private int distanceToTheNextPort;
    private String currentPortName;
    private String lastPortName;
    private long lastPortDepartureTime;


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
}
