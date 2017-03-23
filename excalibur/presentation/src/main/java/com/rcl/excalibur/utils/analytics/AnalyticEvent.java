package com.rcl.excalibur.utils.analytics;


import java.util.HashMap;

public class AnalyticEvent {
    private HashMap<String, Object> keyValues;
    private String eventName;

    public AnalyticEvent(String eventName) {
        this.setEventName(eventName);
        keyValues = new HashMap<>();
    }

    public HashMap<String, Object> getKeyValues() {
        return keyValues;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public AnalyticEvent addKeyValue(String eventKey, Object eventValue) {
        getKeyValues().put(eventKey, eventValue);
        return this;
    }

}
