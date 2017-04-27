package com.rcl.excalibur.data.service.api;


import com.rcl.excalibur.data.service.response.ActivitiesResponse;
import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.DiningsResponse;
import com.rcl.excalibur.data.service.response.EntertainmentsResponse;
import com.rcl.excalibur.data.service.response.ExcursionResponse;
import com.rcl.excalibur.data.service.response.GetMenuResponse;
import com.rcl.excalibur.data.service.response.GetProductsResponse;
import com.rcl.excalibur.data.service.response.GetSubCategoriesResponse;
import com.rcl.excalibur.data.service.response.MenuResponse;
import com.rcl.excalibur.data.service.response.PromotionMessagesResponse;
import com.rcl.excalibur.data.service.response.SpasResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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

    @GET("products/en/royal-mobile")
    Call<GetProductsResponse> getProducts(@Query("sailingID") String sailingID
            , @Query("productType") String type
            , @Query("maxCount") int maxCount);

    @GET("activities/en/mobile")
    Call<ActivitiesResponse> getActivities();

    @GET("categories/en/royal-mobile")
    Call<GetSubCategoriesResponse> getSubCategories(@Query("sailingID") String sailingID);

    @GET()
    Call<GetMenuResponse> getMenus(@Query("sailingID") String sailingID
            , @Query("venueCode") String venueCode);
}
