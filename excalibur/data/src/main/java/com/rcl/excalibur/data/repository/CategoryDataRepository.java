package com.rcl.excalibur.data.repository;


import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.mapper.CategoryEntityDataMapper;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.repository.CategoryRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CategoryDataRepository extends BaseDataRepository<Category, CategoryEntity> implements CategoryRepository {

    private final CategoryEntityDataMapper categoryEntityDataMapper;

    @Inject
    CategoryDataRepository(CategoryEntityDataMapper categoryEntityDataMapper) {
        super(categoryEntityDataMapper, CategoryEntity.class);
        this.categoryEntityDataMapper = categoryEntityDataMapper;
    }

    @Override
    public void create(Category category) {
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
