package com.rcl.excalibur.data.service.api;


import com.rcl.excalibur.data.service.response.WeatherInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MockableApi {

    @GET("v1/weatherInfo/latitude/35/longitude/-46/duration/10")
    Call<WeatherInfoResponse> weatherInfo();
}
