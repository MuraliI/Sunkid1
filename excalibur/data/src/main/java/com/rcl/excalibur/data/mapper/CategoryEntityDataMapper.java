package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.domain.Category;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link CategoryEntity} (in the data layer) to {@link Category} in the
 * domain layer.
 */
@Singleton
public class CategoryEntityDataMapper extends BaseDataMapper<Category, CategoryEntity> {

    @Inject
    CategoryEntityDataMapper() {
    }

    @Override
    public Category transform(final CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        final Category category = new Category();
        category.setCategoryId(entity.getCategoryId());
        category.setDescription(entity.getDescription());
        category.setTags(Arrays.asList(entity.getTags()));
        return category;
    }
}
