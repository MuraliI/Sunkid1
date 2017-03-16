package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.service.DiscoveryService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;


public class GetCategoriesUserCase extends UseCase<List<DiscoverItem>, Void> {

    private final DiscoveryService itineraryService;

    @Inject
    GetCategoriesUserCase(DiscoveryService itineraryService) {
        super();
        this.itineraryService = itineraryService;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<DiscoverItem>> observer, Void aVoid) {
        itineraryService.getItems(observer);
    }

}
