package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.entity.WeatherCurrentEntity;
import com.rcl.excalibur.data.entity.WeatherEntity;
import com.rcl.excalibur.domain.Weather;
import com.rcl.excalibur.domain.WeatherCurrent;

import java.util.ArrayList;
import java.util.List;

public class WeatherCurrentEntityDataMapper extends BaseDataMapper<WeatherCurrent, WeatherCurrentEntity, Void> {

    @Override
    public WeatherCurrent transform(final WeatherCurrentEntity entity, Void additionalArg) {
        if (entity == null) {
            return null;
        }
        WeatherCurrent weatherCurrent = new WeatherCurrent();

        weatherCurrent.setMinTemp(entity.getMinTemp());
        weatherCurrent.setMaxTemp(entity.getMaxTemp());
        weatherCurrent.setHumidity(entity.getHumidity());
        weatherCurrent.setCloudPercent(entity.getCloudPercent());
        weatherCurrent.setLastUpdateTime(entity.getLastUpdateTime());
        weatherCurrent.setSunrise(entity.getSunrise());
        weatherCurrent.setSunset(entity.getSunset());
        weatherCurrent.setPressure(entity.getPressure());
        weatherCurrent.setWindSpeed(entity.getWindSpeed());
        weatherCurrent.setWindDirectionDeg(entity.getWindDirectionDeg());
        weatherCurrent.setCurrentRainFallPercent(entity.getCurrentRainFallPercent());

        List<Weather> weatherList = new ArrayList<>();
        for (WeatherEntity weatherEntity : entity.getWeather()) {
            Weather weather = new Weather();
            weather.setWeatherId(weatherEntity.getWeatherId());
            weather.setDescription(weatherEntity.getDescription());
            weather.setShortDescription(weatherEntity.getShortDescription());
            weather.setIcon(weatherEntity.getIcon());
            weatherList.add(weather);
        }

        weatherCurrent.setWeather(weatherList);

        return weatherCurrent;
    }
}
