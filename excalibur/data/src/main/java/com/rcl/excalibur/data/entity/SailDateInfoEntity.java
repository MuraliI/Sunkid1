package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = SailDateInfoEntity.TABLE_NAME)
public class SailDateInfoEntity extends Model {

    public static final String TABLE_NAME = "saildate";

    public static final String COLUMN_INTINERARY = "itinerary";
    public static final String COLUMN_SHIPCODE = "shipCode";
    public static final String COLUMN_DURATION = "duration";

    @Column(name = COLUMN_INTINERARY)
    private ItineraryEntity itinerary;
    @Column(name = COLUMN_SHIPCODE)
    private String shipCode;
    @Column(name = COLUMN_DURATION)
    private int duration;


    public ItineraryEntity getItinerary() {
        return itinerary;
    }

    public void setItinerary(ItineraryEntity itinerary) {
        this.itinerary = itinerary;
    }

    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
