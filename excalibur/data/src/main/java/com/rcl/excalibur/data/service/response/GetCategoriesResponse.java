package com.rcl.excalibur.data.service.response;

import java.util.List;


public class GetCategoriesResponse extends BaseResponse {

    private List<CategoryResponse> category;

    public GetCategoriesResponse() {
    }

    public List<CategoryResponse> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryResponse> category) {
        this.category = category;
    }

}
