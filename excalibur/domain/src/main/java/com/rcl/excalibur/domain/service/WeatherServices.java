package com.rcl.excalibur.domain.service;

import com.rcl.excalibur.domain.ShipStatsInfo;

import io.reactivex.observers.DisposableObserver;

public interface WeatherServices {
    void weatherInfo(ShipStatsInfo shipStatsInfo, DisposableObserver<Void> observer);
}
