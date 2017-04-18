package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.mapper.CategoryEntityDataMapper;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.repository.CategoryRepository;

import java.util.List;

import io.reactivex.Observer;

public class CategoryDataRepository extends BaseDataRepository<Category, CategoryEntity, Void, CategoryEntityDataMapper>
        implements CategoryRepository {

    public CategoryDataRepository() {
        super(new CategoryEntityDataMapper(), CategoryEntity.class);
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
    public void deleteAll() {
        //no op
    }

    @Override
    public void getAll(Observer<List<Category>> observer) {
        super.getAll(observer);
    }

    @Override
    public Category get(String id) {
        return get(CategoryEntity.COLUMN_CATEGORY_ID, id);
    }

}
