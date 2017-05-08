package com.rcl.excalibur.data.service.api;


import com.rcl.excalibur.data.service.response.WeatherInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {

    @GET("weatherInfo/latitude/{latitude}/longitude/{longitude}/duration/{duration}")
    Call<WeatherInfoResponse> weatherInfo(@Path("latitude") String latitude,
                                          @Path("longitude") String longitude,
                                          @Path("duration") String duration);
}
