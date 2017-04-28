package com.rcl.excalibur.data.service;

import com.rcl.excalibur.data.service.response.GetMenuResponse;
import com.rcl.excalibur.domain.service.MenuServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getMenuApi;


public class MenuServicesImpl implements MenuServices {
    private static final String SAILING_ID = "AL20170430";

    @Override
    public void getMenu() {
        Call<GetMenuResponse> call = getMenuApi().getMenus(SAILING_ID, "GIOV");

        call.enqueue(new Callback<GetMenuResponse>() {
            @Override
            public void onResponse(Call<GetMenuResponse> call, Response<GetMenuResponse> response) {
                Timber.d("Succesfull", response.body().getResponseStatus());
            }

            @Override
            public void onFailure(Call<GetMenuResponse> call, Throwable t) {
                Timber.e("Error", t.getMessage());
            }
        });
    }
}
