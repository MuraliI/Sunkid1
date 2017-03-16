
package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;


public class CategoriesResponse {

    @SerializedName("GetCategoriesResponse")
    private com.rcl.excalibur.data.service.response.GetCategoriesResponse mGetCategoriesResponse;

    public com.rcl.excalibur.data.service.response.GetCategoriesResponse getGetCategoriesResponse() {
        return mGetCategoriesResponse;
    }

    public void setGetCategoriesResponse(com.rcl.excalibur.data.service.response.GetCategoriesResponse getCategoriesResponse) {
        this.mGetCategoriesResponse = getCategoriesResponse;
    }

}
