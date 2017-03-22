package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

public class ExcursionResponse {

    @SerializedName("GetExcursionsResponse")
    private GetProductsResponse getExcursionsResponse;

    public GetProductsResponse getGetExcursionsResponse() {
        return getExcursionsResponse;
    }

    public void setGetExcursionsResponse(GetProductsResponse getExcursionsResponse) {
        this.getExcursionsResponse = getExcursionsResponse;
    }
}
