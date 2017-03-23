package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.service.DiscoverService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;


public class GetProductsUseCase extends UseCase<List<Product>, Void> {

    private final DiscoverService discoverService;

    @Inject
    GetProductsUseCase(DiscoverService discoverService) {
        super();
        this.discoverService = discoverService;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<Product>> observer, Void aVoid) {
        discoverService.getProducts();
    }

}
