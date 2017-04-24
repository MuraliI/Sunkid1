package com.rcl.excalibur.data.entity;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = DeckInfoEntity.TABLE_NAME)
public class DeckInfoEntity {

    public static final String TABLE_NAME = "deck_info";
    public static final String COLUMN_DECK_NUMBER = "deck_number";
    public static final String COLUMN_DIRECTION = "direction";

    @Column(name = COLUMN_DECK_NUMBER)
    private String deckNumber;
    @Column(name = COLUMN_DIRECTION)
    private String direction;

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
}
