package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.WeatherCurrent;
import com.rcl.excalibur.domain.repository.WeatherCurrentRepository;

public class GetWeatherCurrentDbUseCase extends UseCaseSync<WeatherCurrentRepository> {

    public GetWeatherCurrentDbUseCase(WeatherCurrentRepository weatherCurrentRepository) {
        super(weatherCurrentRepository);
    }

    public WeatherCurrent get() {
        return getData().get();
    }
}
