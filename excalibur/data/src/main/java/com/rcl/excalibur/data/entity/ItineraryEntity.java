package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = ItineraryEntity.TABLE_NAME)
public class ItineraryEntity extends Model {
    public static final String TABLE_NAME = "intinerary";

    public static final String COLUMN_DESCRIPTION = "description";

    @Column(name = COLUMN_DESCRIPTION)
    private String description;

    public List<EventEntity> getEvents() {
        return getMany(EventEntity.class, EventEntity.COLUMN_INTINERARY);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

