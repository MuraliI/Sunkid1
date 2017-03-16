package com.rcl.excalibur.data.service.response.mapper;


import com.rcl.excalibur.data.service.response.CategoryResponse;
import com.rcl.excalibur.domain.Category;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CategoryResponseDataMapper extends BaseResponseDataMapper<Category, CategoryResponse> {

    @Inject
    CategoryResponseDataMapper() {

    }

    @Override
    public com.rcl.excalibur.domain.Category transform(CategoryResponse entity) {
        com.rcl.excalibur.domain.Category category = null;
        if (entity != null) {
            category = new com.rcl.excalibur.domain.Category();
            category.setCategoryId(entity.getCategoryid());
            category.setDescription(entity.getCategoryDescription());
            category.setTags(entity.getProductTags());
        }
        return category;
    }
}
