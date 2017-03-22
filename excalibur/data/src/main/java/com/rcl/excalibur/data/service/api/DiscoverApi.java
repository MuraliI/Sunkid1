package com.rcl.excalibur.data.service.api;


import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.EntertaimentsResponse;
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
    Call<EntertaimentsResponse> getEntertaiments();

    @GET("spas/en/mobile")
    Call<SpasResponse> getSpas();

    @GET("products/en/mobile")
    Call<ProductsResponse> getProducts();
}
