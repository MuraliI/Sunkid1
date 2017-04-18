package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.SailDateDataMapper;
import com.rcl.excalibur.data.service.response.SailDateEventResponse;
import com.rcl.excalibur.data.service.response.itinerary.SailDateResponse;
import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.service.SailDateServices;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

import static com.rcl.excalibur.data.utils.ServiceUtil.getSailDateApi;

public class SailDateServicesImpl extends BaseDataService<SailDateEvent, SailDateEventResponse> implements SailDateServices {

    protected SailDateServicesImpl() {
        super(new SailDateDataMapper());
    }

    @Override
    public void getSailDate(Observer<List<SailDateEvent>> observer, String sailId) {
        Observable<List<SailDateEvent>> observable = Observable.create(e -> {
            Call<SailDateResponse> call = getSailDateApi().getEvents(sailId);
            try {
                Response<SailDateResponse> response = call.execute();
                List<SailDateEventResponse> events = response.body()
                        .getSailingInfo()
                        .getItineraries()
                        .get(0)
                        .getEvents();
                List<SailDateEvent> itineraryEventList = getMapper().transform(events);
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
