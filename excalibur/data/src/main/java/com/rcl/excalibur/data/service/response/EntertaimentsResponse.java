package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;


public class EntertaimentsResponse {

    @SerializedName("GetEntertainmentsResponse")
    private GetProductsResponse getEntertaimentsResponse;

    public GetProductsResponse getGetEntertaimentsResponse() {
        return getEntertaimentsResponse;
    }

    public void setGetCategoriesResponse(GetProductsResponse getEntertaimentsResponse) {
        this.getEntertaimentsResponse = getEntertaimentsResponse;
    }

}
