package com.rcl.excalibur.data.entity.itinerary;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = EventLocationEntity.TABLE_NAME)
public class EventLocationEntity extends Model {

    public static final String TABLE_NAME = "event_location";
    public static final String COLUMN_LOCATION_CODE = "location_code";
    public static final String COLUMN_LOCATION_TYPE = "location_type";
    public static final String COLUMN_LOCATION_NAME = "location_name";

    @Column(name = COLUMN_LOCATION_CODE)
    public String locationCode;
    @Column(name = COLUMN_LOCATION_TYPE)
    public String locationType;
    @Column(name = COLUMN_LOCATION_NAME)
    public String locationName;

    public EventLocationEntity() {
        super();
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
