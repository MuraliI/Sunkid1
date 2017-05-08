package com.rcl.excalibur.data.service;

import com.rcl.excalibur.data.service.response.WeatherInfoResponse;
import com.rcl.excalibur.domain.service.WeatherServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getMockableApi;

public class WeatherInfoServicesImpl implements WeatherServices {

    public WeatherInfoServicesImpl() {
    }

    @Override
    public void weatherInfo() {
        Call<WeatherInfoResponse> call = getMockableApi().weatherInfo();

        call.enqueue(new Callback<WeatherInfoResponse>() {
            @Override
            public void onResponse(Call<WeatherInfoResponse> call, Response<WeatherInfoResponse> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Timber.e("Weather success");
            }

            @Override
            public void onFailure(Call<WeatherInfoResponse> call, Throwable t) {
                Timber.e("Weather error", t.getMessage());
            }
        });


    }
}
