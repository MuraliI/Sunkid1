package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = DeckInfoEntity.TABLE_NAME)
public class DeckInfoEntity extends Model {

    public static final String TABLE_NAME = "deck_info";
    public static final String COLUMN_DECK_NUMBER = "deck_number";
    public static final String COLUMN_DIRECTION = "direction";
    public static final String COLUMN_LOCATION = "location";

    @Column(name = COLUMN_DECK_NUMBER)
    private String deckNumber;
    @Column(name = COLUMN_DIRECTION)
    private String direction;
    @Column(name = COLUMN_LOCATION)
    private LocationEntity locationEntity;

    public DeckInfoEntity() {
        super();
    }

    public String getDeckNumber() {
        return deckNumber;
    }

    public void setDeckNumber(String deckNumber) {
        this.deckNumber = deckNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public LocationEntity getLocationEntity() {
        return locationEntity;
    }

    public void setLocationEntity(LocationEntity locationEntity) {
        this.locationEntity = locationEntity;
    }
}
