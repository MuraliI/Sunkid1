package com.rcl.excalibur.model;


import java.util.List;

public class StandardTimeModel {

    private String title;
    private List<TimeModel> times;

    public StandardTimeModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TimeModel> getTimes() {
        return times;
    }

    public void setTimes(List<TimeModel> times) {
        this.times = times;
    }
}
