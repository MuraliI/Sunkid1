package com.rcl.excalibur.data.service.response;

public class WeatherCurrentResponse extends WeatherBaseResponse {

    private long sunrise;
    private long sunset;
    private String windDirectionDeg;
    private String currentRainFallPercent;
    private String cloudPercent;

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
}
