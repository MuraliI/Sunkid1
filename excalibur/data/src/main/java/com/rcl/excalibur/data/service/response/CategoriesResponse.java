package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;


public class CategoriesResponse {

    @SerializedName("GetCategoriesResponse")
    private GetCategoriesResponse getCategoriesResponse;

    public GetCategoriesResponse getGetCategoriesResponse() {
        return getCategoriesResponse;
    }

    public void setGetCategoriesResponse(GetCategoriesResponse getCategoriesResponse) {
        this.getCategoriesResponse = getCategoriesResponse;
    }

}
