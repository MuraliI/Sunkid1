package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.service.response.CategoryResponse;
import com.rcl.excalibur.domain.Category;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CategoryResponseDataMapper extends BaseDataMapper<Category, CategoryResponse> {

    @Inject
    CategoryResponseDataMapper() {
    }

    @Override
    public Category transform(CategoryResponse entity) {
        Category category = null;
        if (entity != null) {
            category = new Category();
            category.setCategoryId(entity.getCategoryid());
            category.setDescription(entity.getCategoryDescription());
            category.setTags(entity.getProductTags());
        }
        return category;
    }
}
