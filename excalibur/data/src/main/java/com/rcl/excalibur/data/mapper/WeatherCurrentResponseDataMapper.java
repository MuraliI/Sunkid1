package com.rcl.excalibur.data.mapper;

import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.WeatherCurrentResponse;
import com.rcl.excalibur.data.service.response.WeatherResponse;
import com.rcl.excalibur.domain.Weather;
import com.rcl.excalibur.domain.WeatherCurrent;

import java.util.ArrayList;
import java.util.List;

public class WeatherCurrentResponseDataMapper extends BaseDataMapper<WeatherCurrent, WeatherCurrentResponse, Void> {

    @Nullable
    @Override
    public WeatherCurrent transform(WeatherCurrentResponse response, Void arg) {

        if (response == null) {
            return new WeatherCurrent();
        }

        WeatherCurrent weatherCurrent = new WeatherCurrent();

        weatherCurrent.setMinTemp(response.getMinTemp());
        weatherCurrent.setMaxTemp(response.getMaxTemp());
        weatherCurrent.setHumidity(response.getHumidity());
        weatherCurrent.setCloudPercent(response.getCloudPercent());
        weatherCurrent.setLastUpdateTime(response.getLastUpdateTime());
        weatherCurrent.setSunrise(response.getSunrise());
        weatherCurrent.setSunset(response.getSunset());
        weatherCurrent.setPressure(response.getPressure());
        weatherCurrent.setWindSpeed(response.getWindSpeed());
        weatherCurrent.setWindDirectionDeg(response.getWindDirectionDeg());

        List<Weather> weatherList = new ArrayList<>();
        for (WeatherResponse weatherResponse : response.getWeather()) {
            Weather weather = new Weather();
            weather.setWeatherId(weatherResponse.getId());
            weather.setDescription(weatherResponse.getDescription());
            weather.setShortDescription(weatherResponse.getShortDescription());
            weather.setIcon(weatherResponse.getIcon());
            weatherList.add(weather);
        }

        weatherCurrent.setWeather(weatherList);

        return weatherCurrent;
    }
}
