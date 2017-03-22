package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;


public class EntertainmentsResponse {

    @SerializedName("GetEntertainmentsResponse")
    private GetProductsResponse getEntertainmentsResponse;

    public GetProductsResponse getGetEntertainmentsResponse() {
        return getEntertainmentsResponse;
    }

    public void setGetCategoriesResponse(GetProductsResponse getEntertainmentsResponse) {
        this.getEntertainmentsResponse = getEntertainmentsResponse;
    }

}
