package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.service.response.CategoryResponse;
import com.rcl.excalibur.domain.Category;

public class CategoryResponseDataMapper extends BaseDataMapper<Category, CategoryResponse, Void> {

    @Override
    public Category transform(CategoryResponse entity, Void additionalArgs) {
        Category category = null;
        if (entity != null) {
            category = new Category();
            category.setCategoryId(entity.getCategoryId());
            category.setDescription(entity.getCategoryDescription());
            category.setTags(entity.getProductTags());
        }
        return category;
    }
}
