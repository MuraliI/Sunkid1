package com.rcl.excalibur.data.service.api;


import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.DiningsResponse;
import com.rcl.excalibur.data.service.response.EntertainmentsResponse;
import com.rcl.excalibur.data.service.response.ExcursionResponse;
import com.rcl.excalibur.data.service.response.ProductsResponse;
import com.rcl.excalibur.data.service.response.PromotionMessagesResponse;
import com.rcl.excalibur.data.service.response.SpasResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DiscoverApi {

    @GET("categories/en/mobile")
    Call<CategoriesResponse> getCategories();

    @GET("promotionmessages/en/mobile")
    Call<PromotionMessagesResponse> getPromotionMessages();

    @GET("entertainments/en/mobile")
    Call<EntertainmentsResponse> getEntertainments();

    @GET("spas/en/mobile")
    Call<SpasResponse> getSpas();

    @GET("excursions/en/mobile")
    Call<ExcursionResponse> getExcursion();

    @GET("dinings/en/mobile")
    Call<DiningsResponse> getDinings();

    @GET("products/en/mobile")
    Call<ProductsResponse> getProducts();
}