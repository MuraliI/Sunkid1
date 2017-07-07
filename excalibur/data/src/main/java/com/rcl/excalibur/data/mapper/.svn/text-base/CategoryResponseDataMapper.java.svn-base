package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.CategoryResponse;
import com.rcl.excalibur.data.service.response.ChildCategoryResponse;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.ChildCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponseDataMapper extends BaseDataMapper<Category, CategoryResponse, Void> {

    @Override
    public Category transform(CategoryResponse response, Void additionalArg) {
        Category category = null;
        if (response != null) {
            category = new Category();
            category.setCategoryDescription(response.getCategoryDescription());
            category.setCategoryId(response.getCategoryId());
            category.setCategoryName(response.getCategoryName());
            category.setChildCategory(transformChildCategories(response.getChildCategory()));
        }
        return category;
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
