package com.rcl.excalibur.data.service.response;

import java.util.List;

public class WeatherInfoResponse {

    private WeatherCurrentResponse current;
    private List<ForecastWeatherResponse> forecast;

    public WeatherCurrentResponse getCurrent() {
        return current;
    }

    public void setCurrent(WeatherCurrentResponse current) {
        this.current = current;
    }

    public List<ForecastWeatherResponse> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastWeatherResponse> forecast) {
        this.forecast = forecast;
    }
}
