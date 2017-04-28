package com.rcl.excalibur.data.service.api;

import com.rcl.excalibur.data.service.response.GetMenuResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MenuApi {
    @GET("venue/menus")
    Call<GetMenuResponse> getMenus(@Query("sailingID") String sailingID
            , @Query("venueCode") String venueCode);
}
