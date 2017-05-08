package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = ShipStatsEntity.TABLE_NAME)
public class ShipStatsEntity extends Model {
    public static final String TABLE_NAME = "ship_stats";
    public static final String COLUMN_SHIP_NAME = "ship_name";
    public static final String COLUMN_NEXT_PORT_NAME = "next_port_name";
    public static final String COLUMN_NEXT_PORT_ARRIVAL_TIME = "next_port_arrival_time";
    public static final String COLUMN_DISTANCE_TO_THE_NEXT_PORT = "distance_next_port";
    public static final String COLUMN_CURRENT_PORT_NAME = "current_port_name";
    public static final String COLUMN_LAST_PORT_NAME = "last_port_name";
    public static final String COLUMN_LAST_PORT_DEPARTURE_TIME = "last_port_departure_time";

    @Column(name = COLUMN_SHIP_NAME)
    private String shipName;
    @Column(name = COLUMN_NEXT_PORT_NAME)
    private String nextPortName;
    @Column(name = COLUMN_NEXT_PORT_ARRIVAL_TIME)
    private long nextPortArrivalTime;
    @Column(name = COLUMN_DISTANCE_TO_THE_NEXT_PORT)
    private int distanceToTheNextPort;
    @Column(name = COLUMN_CURRENT_PORT_NAME)
    private String currentPortName;
    @Column(name = COLUMN_LAST_PORT_NAME)
    private String lastPortName;
    @Column(name = COLUMN_LAST_PORT_DEPARTURE_TIME)
    private long lastPortDepartureTime;

    public ShipStatsEntity() {
        super();
    }

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
