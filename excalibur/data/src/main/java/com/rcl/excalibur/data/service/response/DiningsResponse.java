package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

public class DiningsResponse {

    @SerializedName("GetDiningsResponse")
    private GetProductsResponse getDiningsResponse;

    public GetProductsResponse getGetDiningsResponse() {
        return getDiningsResponse;
    }

    public void setGetDiningsResponse(GetProductsResponse getDiningsResponse) {
        this.getDiningsResponse = getDiningsResponse;
    }
}
