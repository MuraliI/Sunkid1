package com.rcl.excalibur.data.service;


import android.util.Log;

import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.utils.ServiceUtil;
import com.rcl.excalibur.domain.repository.DiscoverService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverServiceImpl implements DiscoverService {


    public void getCategories() {

        Call<CategoriesResponse> call =
                ServiceUtil.getDiscoveryApi().getCategories();

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
    public void getCategoriesResponse() {

    }
}
