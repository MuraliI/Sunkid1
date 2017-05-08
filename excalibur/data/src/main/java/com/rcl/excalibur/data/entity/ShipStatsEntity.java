package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = ShipStatsEntity.TABLE_NAME)
public class ShipStatsEntity extends Model {
    public static final String TABLE_NAME = "ship_stats";
    public static final String COLUMN_SHIP_NAME = "ship_name";

    @Column(name = COLUMN_SHIP_NAME)
    private String shipName;

    public ShipStatsEntity() {
        super();
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
}
