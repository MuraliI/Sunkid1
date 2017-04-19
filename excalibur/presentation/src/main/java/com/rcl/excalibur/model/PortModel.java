
package com.rcl.excalibur.model;

public class PortModel {
    public static final String PORT_TYPE_EMBARK = "EMBARK";
    public static final String PORT_TYPE_DOCKED = "DOCKED";
    public static final String PORT_TYPE_CRUISING = "CRUISING";
    public static final String PORT_TYPE_DEBARK = "DEBARK";

    private String arrivalDate;
    private int arrivalTime;
    private String departureDate;
    private int departureTime;
    private String portCode;
    private String portName;
    private String portType;


    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

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
}
