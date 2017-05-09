package com.rcl.excalibur.domain;


public class ShipLocationStatsInfo {
    private static final String SPEED_VAR = " kts";
    private int aisNavigationalStatus;
    private int speed;
    private int heading;
    private int course;

    public int getAisNavigationalStatus() {
        return aisNavigationalStatus;
    }

    public void setAisNavigationalStatus(int aisNavigationalStatus) {
        this.aisNavigationalStatus = aisNavigationalStatus;
    }

    public int getSpeed() {
        return speed;
    }

    public String getShipSpeed() {
        return speed + SPEED_VAR;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
