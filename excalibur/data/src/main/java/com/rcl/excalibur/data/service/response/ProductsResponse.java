package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;


public class ProductsResponse {

    @SerializedName("GetProductsResponse")
    private GetProductsResponse getProductsResponse;

    public GetProductsResponse getGetProductsResponse() {
        return getProductsResponse;
    }

    public void setGetCategoriesResponse(GetProductsResponse getProductsResponse) {
        this.getProductsResponse = getProductsResponse;
    }

}
