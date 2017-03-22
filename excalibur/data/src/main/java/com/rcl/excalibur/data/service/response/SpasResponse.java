
package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

public class SpasResponse {

    @SerializedName("GetSpasResponse")
    private GetProductsResponse getSpasResponse;

    public GetProductsResponse getGetSpasResponse() {
        return getSpasResponse;
    }

    public void setGetSpasResponse(GetProductsResponse getSpasResponse) {
        this.getSpasResponse = getSpasResponse;
    }
}
