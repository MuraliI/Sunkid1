package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.ShipStatsInfo;
import com.rcl.excalibur.domain.service.WeatherServices;

import io.reactivex.observers.DisposableObserver;

public class GetWeatherCurrentUseCase extends UseCase<Boolean, ShipStatsInfo> {

    private final WeatherServices weatherServices;

    public GetWeatherCurrentUseCase(WeatherServices weatherServices) {
        super();
        this.weatherServices = weatherServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Boolean> observer, ShipStatsInfo shipStatsInfo) {
        weatherServices.weatherInfo(shipStatsInfo, observer);
    }

}
