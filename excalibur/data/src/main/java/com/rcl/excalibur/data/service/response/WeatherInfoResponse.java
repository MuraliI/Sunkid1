package com.rcl.excalibur.data.service.response;

import java.util.List;

public class WeatherInfoResponse {

    private CurrentWeatherResponse current;
    private List<ForecastWeatherResponse> forecast;

    public CurrentWeatherResponse getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeatherResponse current) {
        this.current = current;
    }

    public List<ForecastWeatherResponse> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastWeatherResponse> forecast) {
        this.forecast = forecast;
    }
}
