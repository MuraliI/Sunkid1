package com.rcl.excalibur.data.service.api;


import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.SpasResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DiscoverApi {

    @GET("categories/en/mobile")
    Call<CategoriesResponse> getCategories();


    @GET("spas/en/mobile")
    Call<SpasResponse> getSpas();
}
