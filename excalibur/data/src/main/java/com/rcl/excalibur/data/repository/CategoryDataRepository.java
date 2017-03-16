package com.rcl.excalibur.data.repository;


import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.entity.mapper.CategoryEntityDataMapper;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

@Singleton
public class CategoryDataRepository implements CategoryRepository {

    private final CategoryEntityDataMapper categoryEntityDataMapper;

    @Inject
    CategoryDataRepository(CategoryEntityDataMapper categoryEntityDataMapper) {
        this.categoryEntityDataMapper = categoryEntityDataMapper;
    }

    @Override
    public void create(Category category) {
        final CategoryEntity entity = new CategoryEntity();
//        entity.setCategoryId();
//        entity.setDescription();
//        entity.setTags();
        entity.save();
    }

    @Override
    public List<Category> getAll() {
        final List<CategoryEntity> entities = new Select()
                .from(CategoryEntity.class)
                .execute();
        return categoryEntityDataMapper.transform(entities);
    }

    @Override
    public Category get(long id) {
        final CategoryEntity entity = new Select()
                .from(CategoryEntity.class)
                .where(eq(CategoryEntity.COLUMN_CATEGORY_ID, id))
                .executeSingle();
        return categoryEntityDataMapper.transform(entity);
    }

}
