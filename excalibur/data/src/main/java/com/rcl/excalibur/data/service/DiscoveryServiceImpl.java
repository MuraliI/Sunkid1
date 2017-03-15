package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.service.response.DiscoverItemResponse;
import com.rcl.excalibur.data.utils.ServiceUtils;
import com.rcl.excalibur.domain.service.DiscoveryService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton

public class DiscoveryServiceImpl implements DiscoveryService {

    @Inject
    public DiscoveryServiceImpl() {
    }

    @Override
    public void getItems(Observer observer) {
        Call<List<DiscoverItemResponse>> call = ServiceUtils.getDiscoveryApi().getList();
        call.enqueue(new Callback<List<DiscoverItemResponse>>() {
            @Override
            public void onResponse(Call<List<DiscoverItemResponse>> call, Response<List<DiscoverItemResponse>> response) {
                if (response.isSuccessful()) {
                    //TODO mappear to DiscoverItem. observer.onNext(response.body());
                    return;
                }
                observer.onError(null);
            }

            @Override
            public void onFailure(Call<List<DiscoverItemResponse>> call, Throwable t) {
                observer.onError(t);
            }
        });
    }

    @Override
    public void get() {
    }
}
