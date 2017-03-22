package com.rcl.excalibur.data.service;


import android.util.Log;

import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.EntertainmentsResponse;
import com.rcl.excalibur.data.service.response.PromotionMessagesResponse;
import com.rcl.excalibur.data.service.response.SpasResponse;
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
    public void getSpas() {
        Call<SpasResponse> call = ServiceUtil.getDiscoverApi().getSpas();

        call.enqueue(new Callback<SpasResponse>() {
            @Override
            public void onResponse(Call<SpasResponse> call, Response<SpasResponse> response) {
                Log.d("Succesfull", response.body().getGetSpasResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<SpasResponse> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getPromotionMessages() {
        Call<PromotionMessagesResponse> call = ServiceUtil.getDiscoverApi().getPromotionMessages();
        call.enqueue(new Callback<PromotionMessagesResponse>() {
            @Override
            public void onResponse(Call<PromotionMessagesResponse> call, Response<PromotionMessagesResponse> response) {
                Log.d("Succesfull", response.body().getGetPromotionMessages().getResponseStatus());

            }

            @Override
            public void onFailure(Call<PromotionMessagesResponse> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });
    }


    @Override
    public void getEntertainments() {

        Call<EntertainmentsResponse> call = ServiceUtil.getDiscoverApi().getEntertainments();

        call.enqueue(new Callback<EntertainmentsResponse>() {
            @Override
            public void onResponse(Call<EntertainmentsResponse> call, Response<EntertainmentsResponse> response) {
                Log.d("Succesfull", response.body().getGetEntertainmentsResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<EntertainmentsResponse> call, Throwable t) {
                //Handle failure
                Log.e("error", t.getMessage());
            }
        });
    }


}
