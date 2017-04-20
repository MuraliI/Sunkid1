package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.service.DiscoverServices;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


public class GetProductsUseCase extends UseCase<List<Product>, Void> {

    private final DiscoverServices discoverServices;

    public GetProductsUseCase(DiscoverServices discoverServices) {
        super();
        this.discoverServices = discoverServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<Product>> observer, Void aVoid) {
       discoverServices.getProducts();
    }

}
