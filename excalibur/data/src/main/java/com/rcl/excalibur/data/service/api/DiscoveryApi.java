package com.rcl.excalibur.data.service.api;


import com.rcl.excalibur.data.service.response.DiscoverItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DiscoveryApi {

    @GET("getActivitiesDetail")
    Call<List<DiscoverItemResponse>> getDetails();

    @GET("getActivities")
    Call<List<DiscoverItemResponse>> getList();
}
