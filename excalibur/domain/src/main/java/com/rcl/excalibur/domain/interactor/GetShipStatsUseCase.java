package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.service.ShipStatsServices;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


public class GetShipStatsUseCase extends UseCase<List<ShipStatsModel>, Void> {

    private final ShipStatsServices shipStatsServices;

    public GetShipStatsUseCase(ShipStatsServices shipStatsServices) {
        super();
        this.shipStatsServices = shipStatsServices;
    }


    @Override
    void buildUseCaseObservable(DisposableObserver<List<Shipstats>> observer, Void aVoid) {
        shipStatsServices.getShipStats(observer);
    }
}
