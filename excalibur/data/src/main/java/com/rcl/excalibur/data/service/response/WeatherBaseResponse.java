package com.rcl.excalibur.data.service.response;

public class WeatherBaseResponse {

    private double minTemp;
    private double maxTemp;
    private String pressure;
    private String humidity;
    private String windSpeed;
    private long lastUpdateTime;
    private WeatherResponse weather;

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

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public WeatherResponse getWeather() {
        return weather;
    }

    public void setWeather(WeatherResponse weather) {
        this.weather = weather;
    }
}
