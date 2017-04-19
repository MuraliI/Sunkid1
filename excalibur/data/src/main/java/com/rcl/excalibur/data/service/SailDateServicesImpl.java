package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.SailDateInfoDataMapper;
import com.rcl.excalibur.data.service.response.SailingInfoResponse;
import com.rcl.excalibur.data.service.response.itinerary.SailDateResponse;
import com.rcl.excalibur.domain.SailDateInfoEvent;
import com.rcl.excalibur.domain.service.SailDateServices;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

import static com.rcl.excalibur.data.utils.ServiceUtil.getSailDateApi;

public class SailDateServicesImpl extends BaseDataService<SailDateInfoEvent, SailingInfoResponse, Void> implements SailDateServices {

    protected SailDateServicesImpl() {
        super(new SailDateInfoDataMapper());
    }

    @Override
    public void getSailDate(Observer<SailDateInfoEvent> observer, String sailId) {
        Observable<SailDateInfoEvent> observable = Observable.create(e -> {
            Call<SailDateResponse> call = getSailDateApi().getEvents(sailId);
            try {
                Response<SailDateResponse> response = call.execute();
                SailingInfoResponse sailingInfoResponse = response.body()
                        .getSailingInfo();
                SailDateInfoEvent sailDateInfoEvent = getMapper().transform(sailingInfoResponse, null);
                e.onNext(sailDateInfoEvent);
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
