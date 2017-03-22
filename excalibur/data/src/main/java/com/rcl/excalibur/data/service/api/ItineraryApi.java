package com.rcl.excalibur.data.service.api;

import com.rcl.excalibur.data.service.response.itinerary.BaseResponseItinerary;
import com.rcl.excalibur.data.service.response.itinerary.EventGroupResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ItineraryApi {

    @GET("my_itinerary")
    Call<BaseResponseItinerary<EventGroupResponse>> myItinerary();

}
