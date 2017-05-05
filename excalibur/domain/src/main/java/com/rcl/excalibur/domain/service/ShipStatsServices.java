package com.rcl.excalibur.domain.service;


import io.reactivex.observers.DisposableObserver;

public interface ShipStatsServices {
    void getShipStats(DisposableObserver<Boolean> productsObtained);
}
