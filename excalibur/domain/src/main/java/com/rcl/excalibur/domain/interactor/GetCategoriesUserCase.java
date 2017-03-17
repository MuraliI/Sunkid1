package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.service.DiscoverService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;


public class GetCategoriesUserCase extends UseCase<List<Category>, Void> {

    private final DiscoverService discoverService;

    @Inject
    GetCategoriesUserCase(DiscoverService discoverService) {
        super();
        this.discoverService = discoverService;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<Category>> observer, Void aVoid) {
        discoverService.getCategories();
    }

}
