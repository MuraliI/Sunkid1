package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = WeatherEntity.TABLE_NAME)
public class WeatherEntity extends Model {

    public static final String TABLE_NAME = "weather";

    public static final String COLUMN_WEATHER_ID = "weather_id";
    public static final String COLUMN_WEATHER = "weather_ref";
    public static final String COLUMN_SHORT_DESCRIPTION = "short_direction";
    public static final String COLUMN_DESCRIPTON = "description";
    public static final String COLUMN_ICON = "icon";

    @Column(name = COLUMN_WEATHER_ID)
    private int weatherId;
    @Column(name = COLUMN_SHORT_DESCRIPTION)
    private String shortDescription;
    @Column(name = COLUMN_DESCRIPTON)
    private String description;
    @Column(name = COLUMN_ICON)
    private String icon;
    //FIXME this field has to reference weatherCurrent or weatherForecast
    @Column(name = COLUMN_WEATHER, onDelete = Column.ForeignKeyAction.CASCADE)
    private WeatherCurrentEntity weather;

    public WeatherEntity() {
        super();
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public WeatherCurrentEntity getWeather() {
        return weather;
    }

    public void setWeather(WeatherCurrentEntity weather) {
        this.weather = weather;
    }
}
