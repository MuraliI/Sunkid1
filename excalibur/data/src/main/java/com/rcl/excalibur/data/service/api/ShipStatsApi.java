package com.rcl.excalibur.data.service.api;


import com.rcl.excalibur.data.service.response.ShipStatsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShipStatsApi {


    @GET("ships/AL/stats")
    Call<ShipStatsResponse> getShipStats();
}
