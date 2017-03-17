package com.rcl.excalibur.data.service.response;

import java.util.List;


public class GetCategoriesResponse {

    private List<CategoryResponse> category;
    private String responseStatus;

    public GetCategoriesResponse() {
    }

    public List<CategoryResponse> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryResponse> category) {
        this.category = category;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
