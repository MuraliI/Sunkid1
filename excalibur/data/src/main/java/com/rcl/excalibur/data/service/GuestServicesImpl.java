package com.rcl.excalibur.data.service;

import com.rcl.excalibur.data.mapper.guest.SecurityQuestionsResponseMapper;
import com.rcl.excalibur.data.service.api.GuestApi;
import com.rcl.excalibur.data.service.response.guest.SecurityQuestionsResponse;
import com.rcl.excalibur.domain.service.GuestServices;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

public class GuestServicesImpl implements GuestServices {
    private final GuestApi guestApi;
    private final SecurityQuestionsResponseMapper securityQuestionsResponseMapper;

    public GuestServicesImpl(GuestApi guestApi, SecurityQuestionsResponseMapper securityQuestionsResponseMapper) {
        this.guestApi = guestApi;
        this.securityQuestionsResponseMapper = securityQuestionsResponseMapper;
    }

    @Override
    public void getSecurityQuestions(Observer<List<String>> observer) {
        Observable<List<String>> observable = Observable.create(e -> {
            Call<SecurityQuestionsResponse> call = guestApi.getSecurityQuestions();
            try {
                Response<SecurityQuestionsResponse> response = call.execute();
                SecurityQuestionsResponse securityQuestionsResponse = response.body();
                if (securityQuestionsResponse != null) {
                    e.onNext(securityQuestionsResponseMapper.transform(securityQuestionsResponse));
                } else {
                    e.onError(new RuntimeException("Invalid Response"));
                }
            } catch (IOException ex) {
                e.onError(ex);
            }
            e.onComplete();
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
