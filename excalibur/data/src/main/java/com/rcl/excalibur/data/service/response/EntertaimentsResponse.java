package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;


public class EntertaimentsResponse {

    @SerializedName("GetEntertainmentsResponse")
    private GetEntertaimentsResponse getEntertaimentsResponse;

    public GetEntertaimentsResponse getGetEntertaimentsResponse() {
        return getEntertaimentsResponse;
    }

    public void setGetCategoriesResponse(GetEntertaimentsResponse getEntertaimentsResponse) {
        this.getEntertaimentsResponse = getEntertaimentsResponse;
    }

}
