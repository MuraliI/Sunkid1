package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.ChildCategoryResponse;
import com.rcl.excalibur.data.service.response.SubCategoryResponse;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.ChildCategory;
import com.rcl.excalibur.domain.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryResponseDataMapper extends BaseDataMapper<SubCategory, SubCategoryResponse> {

    @Override
    public SubCategory transform(SubCategoryResponse subCategoryResponse) {
        SubCategory subCategory = null;
        if (subCategoryResponse != null) {
            subCategory = new SubCategory();
            subCategory.setCategoryDescription(subCategoryResponse.getCategoryDescription());
            subCategory.setCategoryId(subCategoryResponse.getCategoryId());
            subCategory.setCategoryName(subCategoryResponse.getCategoryName());
            subCategory.setChildCategory(transformChildCategories(subCategoryResponse.getChildCategory()));
        }
        return subCategory;
    }

    private List<ChildCategory> transformChildCategories(List<ChildCategoryResponse> childCategoryResponses) {

        ArrayList<ChildCategory> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(childCategoryResponses)) {
            return items;
        }
        for (ChildCategoryResponse childCategoryResponse : childCategoryResponses) {

            if (childCategoryResponse == null) {
                continue;
            }

            ChildCategory childCategory = new ChildCategory();
            childCategory.getItems().setCategoryId(childCategoryResponse.getItems().getCategoryId());
            childCategory.getItems().setCategoryName(childCategoryResponse.getItems().getCategoryName());
            childCategory.getItems().setCategoryDescription(childCategoryResponse.getItems().getCategoryDescription());

            items.add(childCategory);
        }

        return items;
    }

}
