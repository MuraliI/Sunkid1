package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.service.DiscoverServices;

import io.reactivex.observers.DisposableObserver;


public class GetProductsUseCase extends UseCase<Boolean, Void> {

    private final DiscoverServices discoverServices;

    public GetProductsUseCase(DiscoverServices discoverServices) {
        super();
        this.discoverServices = discoverServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Boolean> observer, Void aVoid) {
        discoverServices.getProducts(observer);
    }

}
