package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.service.ShipStatsServices;

import io.reactivex.observers.DisposableObserver;


public class GetShipStatsUseCase extends UseCase<Boolean, Void> {

    private final ShipStatsServices shipStatsServices;

    public GetShipStatsUseCase(ShipStatsServices shipStatsServices) {
        super();
        this.shipStatsServices = shipStatsServices;
    }


    @Override
    void buildUseCaseObservable(DisposableObserver<Boolean> observer, Void aVoid) {
        shipStatsServices.getShipStats(observer);
    }
}
