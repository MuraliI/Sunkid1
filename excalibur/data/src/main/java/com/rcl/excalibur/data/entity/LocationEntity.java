package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = LocationEntity.TABLE_NAME)
public class LocationEntity extends Model {

    public static final String TABLE_NAME = "location";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_HOURS_START = "hours_start";
    public static final String COLUMN_HOURS_END = "hours_end";
    public static final String COLUMN_VENUE = "venue";
    public static final String COLUMN_PORT = "port";
    public static final String COLUMN_DECK_NUMBER = "deck_number";
    public static final String COLUMN_DIRECTION = "direction";

    @Column(name = COLUMN_CODE)
    private String code;
    @Column(name = COLUMN_TYPE)
    private String type;
    @Column(name = COLUMN_HOURS_START)
    private String hoursStart;
    @Column(name = COLUMN_HOURS_END)
    private String hoursEnd;
    @Column(name = COLUMN_VENUE)
    private String venue;
    @Column(name = COLUMN_PORT)
    private String port;
    @Column(name = COLUMN_DECK_NUMBER)
    private int deckNumber;
    @Column(name = COLUMN_DIRECTION)
    private String direction;

    public LocationEntity() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHoursStart() {
        return hoursStart;
    }

    public void setHoursStart(String hoursStart) {
        this.hoursStart = hoursStart;
    }

    public String getHoursEnd() {
        return hoursEnd;
    }

    public void setHoursEnd(String hoursEnd) {
        this.hoursEnd = hoursEnd;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getDeckNumber() {
        return deckNumber;
    }

    public void setDeckNumber(int deckNumber) {
        this.deckNumber = deckNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
