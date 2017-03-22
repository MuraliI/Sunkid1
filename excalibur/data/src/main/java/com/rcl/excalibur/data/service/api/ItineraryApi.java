package com.rcl.excalibur.data.service.api;

import com.rcl.excalibur.data.service.response.itinerary.ResponseItinerary;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ItineraryApi {

    @GET("my_itinerary")
    Call<ResponseItinerary> myItinerary();

}
