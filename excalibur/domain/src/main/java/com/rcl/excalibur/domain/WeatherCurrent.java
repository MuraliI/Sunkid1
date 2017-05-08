package com.rcl.excalibur.domain;

import java.util.List;
import java.util.Locale;

public class WeatherCurrent {

    private List<Weather> weather;
    private long sunrise;
    private long sunset;
    private double minTemp;
    private double maxTemp;
    private String pressure;
    private String humidity;
    private String windSpeed;
    private String windDirectionDeg;
    private String currentRainFallPercent;
    private String cloudPercent;
    private long lastUpdateTime;

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

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }


    public String getWeatherStats() {
        return String.format(Locale.getDefault(), "%.0f/%.0fºF", getMinTemp(), getMaxTemp());
    }
}
