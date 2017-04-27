
package com.rcl.excalibur.model;

import android.support.annotation.NonNull;

import java.util.List;

public class PortModel {
    public static final String PORT_TYPE_EMBARK = "EMBARK";
    public static final String PORT_TYPE_DOCKED = "DOCKED";
    public static final String PORT_TYPE_CRUISING = "CRUISING";
    public static final String PORT_TYPE_DEBARK = "DEBARK";

    private String arrivalDate;
    private String arrivalTime;
    private String departureDate;
    private String departureTime;
    private String portCode;
    private String portName;
    private String portType;


    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
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

    public static PortModel getSailPortByDay(@NonNull List<EventModel> events, int day) {
        PortModel sailPort = new PortModel();
        for (EventModel sailPortEventElement : events) {
            if (Integer.valueOf(sailPortEventElement.getDay()) == day) {
                sailPort = sailPortEventElement.getPort();
            }
        }
        return sailPort;
    }
}
