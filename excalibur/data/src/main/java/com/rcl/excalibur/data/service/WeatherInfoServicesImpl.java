package com.rcl.excalibur.data.service;

import com.rcl.excalibur.data.mapper.WeatherCurrentResponseDataMapper;
import com.rcl.excalibur.data.service.response.WeatherCurrentResponse;
import com.rcl.excalibur.data.service.response.WeatherInfoResponse;
import com.rcl.excalibur.domain.WeatherCurrent;
import com.rcl.excalibur.domain.repository.WeatherCurrentRepository;
import com.rcl.excalibur.domain.service.WeatherServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getWeatherApi;

public class WeatherInfoServicesImpl extends BaseDataService<WeatherCurrent, WeatherCurrentResponse, Void> implements WeatherServices {

    WeatherCurrentRepository repository;

    public WeatherInfoServicesImpl(WeatherCurrentRepository repository) {
        super(new WeatherCurrentResponseDataMapper());
        this.repository = repository;
    }

    @Override
    public void weatherInfo() {
        Call<WeatherInfoResponse> call = getWeatherApi().weatherInfo();

        call.enqueue(new Callback<WeatherInfoResponse>() {
            @Override
            public void onResponse(Call<WeatherInfoResponse> call, Response<WeatherInfoResponse> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                WeatherCurrent weatherCurrent = getMapper().transform(response.body().getCurrent(), null);
                repository.deleteAll();
                repository.create(weatherCurrent);
            }

            @Override
            public void onFailure(Call<WeatherInfoResponse> call, Throwable t) {
                Timber.e("Weather error", t.getMessage());
            }
        });


    }
}
