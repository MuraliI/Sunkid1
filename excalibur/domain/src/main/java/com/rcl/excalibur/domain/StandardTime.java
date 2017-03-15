package com.rcl.excalibur.domain;


import java.util.List;

public class StandardTime {

    private String title;
    private List<Time> times;

    public StandardTime() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }
}
