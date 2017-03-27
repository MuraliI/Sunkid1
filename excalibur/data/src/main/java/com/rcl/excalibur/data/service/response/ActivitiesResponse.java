package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

public class ActivitiesResponse {

    @SerializedName("GetActivitiesResponse")
    private GetProductsResponse getActivitiesResponse;

    public GetProductsResponse getGetActivitiesResponse() {
        return getActivitiesResponse;
    }

    public void setGetActivitiesResponse(GetProductsResponse getActivitiesResponse) {
        this.getActivitiesResponse = getActivitiesResponse;
    }
}
