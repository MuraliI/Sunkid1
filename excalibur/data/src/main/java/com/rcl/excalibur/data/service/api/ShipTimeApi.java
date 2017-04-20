package com.rcl.excalibur.data.service.api;


import com.rcl.excalibur.data.service.response.ShipTimeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShipTimeApi {

    @GET("ships/AL/time")
    Call<ShipTimeResponse> getShipTime();
}
