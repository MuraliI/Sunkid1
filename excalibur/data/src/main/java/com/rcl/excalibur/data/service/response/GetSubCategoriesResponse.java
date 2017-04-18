package com.rcl.excalibur.data.service.response;

import java.util.List;


public class GetSubCategoriesResponse extends BaseResponse {

    private List<SubCategoryResponse> category;

    public GetSubCategoriesResponse() {
    }

    public List<SubCategoryResponse> getCategory() {
        return category;
    }

    public void setCategory(List<SubCategoryResponse> category) {
        this.category = category;
    }

}
