package com.rcl.excalibur.data.service;


import android.util.Log;

import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.EntertaimentsResponse;
import com.rcl.excalibur.data.utils.ServiceUtil;
import com.rcl.excalibur.domain.service.DiscoverService;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class DiscoverServiceImpl implements DiscoverService {
    @Inject
    public DiscoverServiceImpl() {
    }

    @Override
    public void getCategories() {

        Call<CategoriesResponse> call = ServiceUtil.getDiscoverApi().getCategories();

        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                Log.d("Succesfull", response.body().getGetCategoriesResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                //Handle failure
                Log.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getEntertaiments() {

        Call<EntertaimentsResponse> call = ServiceUtil.getDiscoverApi().getEntertaiments();

        call.enqueue(new Callback<EntertaimentsResponse>() {
            @Override
            public void onResponse(Call<EntertaimentsResponse> call, Response<EntertaimentsResponse> response) {
                Log.d("Succesfull", response.body().getGetEntertaimentsResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<EntertaimentsResponse> call, Throwable t) {
                //Handle failure
                Log.e("error", t.getMessage());
            }
        });
    }


}
