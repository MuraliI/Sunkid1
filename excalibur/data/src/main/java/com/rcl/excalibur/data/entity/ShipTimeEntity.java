package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = ShipTimeEntity.TABLE_NAME)
public class ShipTimeEntity extends Model {

    public static final String TABLE_NAME = "ship_time";

    public static final String COLUMN_OFFSET = "offset";

    @Column(name = COLUMN_OFFSET)
    public String offset;

    public ShipTimeEntity() {
        super();
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}
