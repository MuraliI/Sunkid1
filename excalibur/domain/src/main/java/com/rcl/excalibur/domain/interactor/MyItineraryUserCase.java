package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.service.ItineraryService;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.observers.DisposableObserver;

public class MyItineraryUserCase extends UseCase<List<Category>, Void> {

    private final ItineraryService itineraryService;

    @Inject
    MyItineraryUserCase(ItineraryService itineraryService) {
        super();
        this.itineraryService = itineraryService;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<Category>> observer, Void aVoid) {
        itineraryService.myItinerary();
    }

}
