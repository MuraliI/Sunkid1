package com.rcl.excalibur.data.service;

import com.rcl.excalibur.data.mapper.itinerary.ItineraryEventDataMapper;
import com.rcl.excalibur.data.service.response.itinerary.ItineraryEventResponse;
import com.rcl.excalibur.data.service.response.itinerary.ItineraryResponse;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.PlannerService;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

import static com.rcl.excalibur.data.utils.ServiceUtil.getItineraryApi;

public class PlannerServiceImpl extends BaseDataService<ItineraryEvent, ItineraryEventResponse> implements PlannerService {

    public PlannerServiceImpl() {
        super(new ItineraryEventDataMapper());
    }

    public void myItinerary(Observer<List<ItineraryEvent>> observer) {

        Observable<List<ItineraryEvent>> observable = Observable.create(e -> {
            Call<ItineraryResponse> call = getItineraryApi().myItinerary();
            try {
                Response<ItineraryResponse> response = call.execute();
                /* FIXME: This won't work if the response has several EventGroup, only for mocking Data with COMMERCE EVENT*/
                List<ItineraryEventResponse> itineraryEventResponseList = response.body()
                        .getItineraryEventGroups()
                        .get(0).getItineraryEvents();
                List<ItineraryEvent> itineraryEventList = getMapper().transform(itineraryEventResponseList);

                e.onNext(itineraryEventList);
            } catch (IOException exception) {
                e.onError(exception);
            }
            e.onComplete();
        });

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}

