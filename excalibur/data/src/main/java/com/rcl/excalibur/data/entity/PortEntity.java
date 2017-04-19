package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = PortEntity.TABLE_NAME)
public class PortEntity extends Model {

    public static final String TABLE_NAME = "port";

    public static final String COLUMN_PORT_CODE = "port_code";
    public static final String COLUMN_PORT_NAME = "port_name";
    public static final String COLUMN_PORT_TIPE = "port_type";
    public static final String COLUMN_ARRIVAL_DATE = "arrival_date";
    public static final String COLUMN_DEAPARTURE_DATE = "departure_date";
    public static final String COLUMN_ARRIVAL_TIME = "arrival_time";
    public static final String COLUMN_DEAPARTURE_TIME = "departure_time";

    @Column(name = COLUMN_PORT_CODE)
    private String portCode;
    @Column(name = COLUMN_PORT_NAME)
    private String portName;
    @Column(name = COLUMN_PORT_TIPE)
    private String portType;
    @Column(name = COLUMN_ARRIVAL_DATE)
    private String arrivalDate;
    @Column(name = COLUMN_DEAPARTURE_DATE)
    private String departureDate;
    @Column(name = COLUMN_ARRIVAL_TIME)
    private int arrivalTime;
    @Column(name = COLUMN_DEAPARTURE_TIME)
    private int departureTime;

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getPortType() {
        return portType;
    }

    public void setPortType(String portType) {
        this.portType = portType;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }
}
