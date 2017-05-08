package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.service.WeatherServices;

import io.reactivex.observers.DisposableObserver;

public class GetWeatherCurrentUseCase extends UseCase<Void, Void> {

    private final WeatherServices weatherServices;

    public GetWeatherCurrentUseCase(WeatherServices weatherServices) {
        super();
        this.weatherServices = weatherServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Void> observer, Void aVoid) {
        weatherServices.weatherInfo();
    }

}
