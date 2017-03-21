package com.rcl.excalibur.data.service.api;


import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.EntertaimentsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DiscoverApi {

    @GET("categories/en/mobile")
    Call<CategoriesResponse> getCategories();


    @GET("entertainments/en/mobile")
    Call<EntertaimentsResponse> getEntertaiments();
}
