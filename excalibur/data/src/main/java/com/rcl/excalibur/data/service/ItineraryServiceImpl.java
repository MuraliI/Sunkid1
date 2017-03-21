package com.rcl.excalibur.data.service;

import android.util.Log;

import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.utils.ServiceUtil;
import com.rcl.excalibur.domain.service.ItineraryService;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ItineraryServiceImpl implements ItineraryService {

    @Inject
    public ItineraryServiceImpl() {
    }

    @Override
    public void myItinerary() {
        Call<CategoriesResponse> call = ServiceUtil.getItineraryApi().myItinerary();

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
}

