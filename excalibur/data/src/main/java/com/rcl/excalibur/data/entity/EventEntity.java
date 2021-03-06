package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = EventEntity.TABLE_NAME)
public class EventEntity extends Model {

    public static final String TABLE_NAME = "events";

    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_PORT = "port";
    public static final String COLUMN_INTINERARY = "intinerary";

    @Column(name = COLUMN_DAY)
    private String day;
    @Column(name = COLUMN_INTINERARY)
    private ItineraryEntity intinerary;

    @Column(name = COLUMN_PORT)
    private PortEntity port;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    public PortEntity getPort() {
        return port;
    }

    public void setPort(PortEntity port) {
        this.port = port;
    }

    public ItineraryEntity getIntinerary() {
        return intinerary;
    }

    public void setIntinerary(ItineraryEntity intinerary) {
        this.intinerary = intinerary;
    }
}

