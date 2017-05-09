package com.rcl.excalibur.data.repository;

import android.support.annotation.NonNull;

import com.activeandroid.query.Delete;
import com.rcl.excalibur.data.entity.WeatherCurrentEntity;
import com.rcl.excalibur.data.entity.WeatherEntity;
import com.rcl.excalibur.data.mapper.WeatherCurrentEntityDataMapper;
import com.rcl.excalibur.domain.Weather;
import com.rcl.excalibur.domain.WeatherCurrent;
import com.rcl.excalibur.domain.repository.WeatherCurrentRepository;

import java.util.ArrayList;
import java.util.List;

public class WeatherCurrentDataRepository extends BaseDataRepository<WeatherCurrent,
        WeatherCurrentEntity, Void, WeatherCurrentEntityDataMapper> implements WeatherCurrentRepository {

    public WeatherCurrentDataRepository() {
        super(new WeatherCurrentEntityDataMapper(), WeatherCurrentEntity.class);
    }

    @Override
    public void create(@NonNull WeatherCurrent weatherCurrent) {
        WeatherCurrentEntity entity = new WeatherCurrentEntity();

        entity.setMinTemp(weatherCurrent.getMinTemp());
        entity.setMaxTemp(weatherCurrent.getMaxTemp());
        entity.setHumidity(weatherCurrent.getHumidity());
        entity.setCloudPercent(weatherCurrent.getCloudPercent());
        entity.setLastUpdateTime(weatherCurrent.getLastUpdateTime());
        entity.setSunrise(weatherCurrent.getSunrise());
        entity.setSunset(weatherCurrent.getSunset());
        entity.setPressure(weatherCurrent.getPressure());
        entity.setWindSpeed(weatherCurrent.getWindSpeed());
        entity.setWindDirectionDeg(weatherCurrent.getWindDirectionDeg());
        entity.setCurrentRainFallPercent(weatherCurrent.getCurrentRainFallPercent());

        //Create Weather List
        create(entity, weatherCurrent.getWeather());

        entity.save();
    }

    private void create(WeatherCurrentEntity entity, List<Weather> weather) {
        List<WeatherEntity> weatherList = new ArrayList<>();
        for (Weather weatherResponse : weather) {
            WeatherEntity weatherEntity = new WeatherEntity();
            weatherEntity.setWeatherId(weatherResponse.getWeatherId());
            weatherEntity.setDescription(weatherResponse.getDescription());
            weatherEntity.setShortDescription(weatherResponse.getShortDescription());
            weatherEntity.setIcon(weatherResponse.getIcon());
            weatherEntity.setWeather(entity);
            weatherEntity.save();

            weatherList.add(weatherEntity);
        }
    }

    @Override
    public void deleteAll() {
        new Delete().from(WeatherCurrentEntity.class).execute();
    }
}
