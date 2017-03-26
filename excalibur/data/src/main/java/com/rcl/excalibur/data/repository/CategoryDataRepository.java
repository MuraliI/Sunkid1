package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.mapper.CategoryEntityDataMapper;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.repository.CategoryRepository;

public class CategoryDataRepository extends BaseDataRepository<Category, CategoryEntity> implements CategoryRepository {

    public CategoryDataRepository(CategoryEntityDataMapper categoryEntityDataMapper) {
        super(categoryEntityDataMapper, CategoryEntity.class);

    }

    @Override
    public void create(@NonNull Category category) {
        final CategoryEntity entity = new CategoryEntity();
        entity.setCategoryId(category.getCategoryId());
        entity.setDescription(category.getDescription());
        entity.setTags(category.getTags());
        entity.save();
    }

    @Override
    public Category get(long id) {
        return get(CategoryEntity.COLUMN_CATEGORY_ID, id);
    }

}
