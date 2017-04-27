package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.service.DiscoverServices;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


public class GetMenuesUseCase extends UseCase<List<Menu>, Void> {

    private final DiscoverServices discoverServices;

    public GetMenuesUseCase(DiscoverServices discoverServices) {
        super();
        this.discoverServices = discoverServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<Menu>> observer, Void aVoid) {
        discoverServices.getMenues();
    }
}
