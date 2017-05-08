package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = WeatherCurrentEntity.TABLE_NAME)
public class WeatherCurrentEntity extends Model {

    public static final String TABLE_NAME = "weather_current";

    public static final String COLUMN_SUNRISE = "sunrise";
    public static final String COLUMN_SUNSET = "sunset";
    public static final String COLUMN_MIN_TEMP = "min_temp";
    public static final String COLUMN_MAX_TEMP = "max_temp";
    public static final String COLUMN_PRESSURE = "pressure";
    public static final String COLUMN_HUMIDITY = "humidity";
    public static final String COLUMN_WIND_SPEED = "wind_speed";
    public static final String COLUMN_WIND_DIRECTION_DEG = "wind_direction_deg";
    public static final String COLUMN_CURRENT_RAIN_FALL_PERCENT = "current_rain_fall_percent";
    public static final String COLUMN_CLOUD_PERCENT = "cloud_percent";
    public static final String COLUMN_LAST_UPDATE_TIME = "last_update_time";

    @Column(name = COLUMN_SUNRISE)
    private long sunrise;
    @Column(name = COLUMN_SUNSET)
    private long sunset;
    @Column(name = COLUMN_MIN_TEMP)
    private double minTemp;
    @Column(name = COLUMN_MAX_TEMP)
    private double maxTemp;
    @Column(name = COLUMN_PRESSURE)
    private String pressure;
    @Column(name = COLUMN_HUMIDITY)
    private String humidity;
    @Column(name = COLUMN_WIND_SPEED)
    private String windSpeed;
    @Column(name = COLUMN_WIND_DIRECTION_DEG)
    private String windDirectionDeg;
    @Column(name = COLUMN_CURRENT_RAIN_FALL_PERCENT)
    private String currentRainFallPercent;
    @Column(name = COLUMN_CLOUD_PERCENT)
    private String cloudPercent;
    @Column(name = COLUMN_LAST_UPDATE_TIME)
    private long lastUpdateTime;

    public WeatherCurrentEntity() {
        super();
    }

    public List<WeatherEntity> getWeather() {
        return getMany(WeatherEntity.class, WeatherEntity.COLUMN_WEATHER);
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirectionDeg() {
        return windDirectionDeg;
    }

    public void setWindDirectionDeg(String windDirectionDeg) {
        this.windDirectionDeg = windDirectionDeg;
    }

    public String getCurrentRainFallPercent() {
        return currentRainFallPercent;
    }

    public void setCurrentRainFallPercent(String currentRainFallPercent) {
        this.currentRainFallPercent = currentRainFallPercent;
    }

    public String getCloudPercent() {
        return cloudPercent;
    }

    public void setCloudPercent(String cloudPercent) {
        this.cloudPercent = cloudPercent;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
