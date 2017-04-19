package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.SubCategory;
import com.rcl.excalibur.domain.service.DiscoverServices;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


public class GetSubCategoriesUseCase extends UseCase<List<SubCategory>, Void> {

    private final DiscoverServices discoverServices;

    public GetSubCategoriesUseCase(DiscoverServices discoverServices) {
        super();
        this.discoverServices = discoverServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<SubCategory>> observer, Void aVoid) {
        discoverServices.getSubCategories();
    }

}
