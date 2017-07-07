package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.service.ShipTimeServices;

import io.reactivex.observers.DisposableObserver;


public class GetShipTimeUseCase extends UseCase<Void, Void> {

    private final ShipTimeServices shipTimeServices;

    public GetShipTimeUseCase(ShipTimeServices shipTimeServices) {
        super();
        this.shipTimeServices = shipTimeServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Void> observer, Void aVoid) {
        shipTimeServices.getShipTime();
    }

}
