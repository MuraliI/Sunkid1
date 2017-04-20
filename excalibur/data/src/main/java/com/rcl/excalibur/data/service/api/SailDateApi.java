package com.rcl.excalibur.data.service.api;


import com.rcl.excalibur.data.service.response.itinerary.SailDateResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SailDateApi {

    @GET("voyages/ships/AD/sailDate/{sailId}")
    Call<SailDateResponse> getEvents(@Path("sailId") String sailId);
}
