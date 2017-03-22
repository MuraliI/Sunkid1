package com.rcl.excalibur.data.service.response.itinerary;

import com.rcl.excalibur.data.utils.DateUtil;

import java.util.Date;

public class EventTimeResponse {
    private String eventDate;
    private String eventTime;
    private String eventTimeZone;

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventTimeZone() {
        return eventTimeZone;
    }

    public void setEventTimeZone(String eventTimeZone) {
        this.eventTimeZone = eventTimeZone;
    }

    public Date getDateObj() {
        Date date = DateUtil.parseDateResponse(eventDate, eventTime);
        return date;
    }
}
