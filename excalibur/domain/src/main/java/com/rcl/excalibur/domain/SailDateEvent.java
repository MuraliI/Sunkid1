package com.rcl.excalibur.domain;

public class SailDateEvent {
    private int day;
    private SailPort port;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public SailPort getPort() {
        return port;
    }

    public void setPort(SailPort port) {
        this.port = port;
    }
}
