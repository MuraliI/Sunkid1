package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.entity.ChildCategoryEntity;
import com.rcl.excalibur.data.mapper.CategoryEntityDataMapper;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.data.utils.DBUtil;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.ChildCategory;
import com.rcl.excalibur.domain.repository.CategoryRepository;

import java.util.List;

public class CategoriesDataRepository extends BaseDataRepository<Category, CategoryEntity, Void, CategoryEntityDataMapper>
        implements CategoryRepository {

    public CategoriesDataRepository() {
        super(new CategoryEntityDataMapper(), CategoryEntity.class);
    }

    @Override
    public void create(List<Category> categories) {
        ActiveAndroid.beginTransaction();
        try {
            for (Category category : categories) {
                create(category);
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    @Override
    public void create(@NonNull Category category) {
        CategoryEntity entity = getEntity(CategoryEntity.COLUMN_CATEGORY_ID, category.getCategoryId());
        if (entity != null) {
            delete(entity);
        }
        entity = new CategoryEntity();
        entity.setCategoryId(category.getCategoryId());
        entity.setDescription(category.getCategoryDescription());
        entity.setName(category.getCategoryName());
        entity.save();

        createChildCategories(entity, category.getChildCategory());
    }

    @Override
    public void deleteAll() {
    }

    public void delete(CategoryEntity entity) {
//   TODO not neccessary, remove for fK    new Delete().from(ChildCategoryEntity.class).execute();
        new Delete().from(CategoryEntity.class).where(DBUtil.eqId(entity.getId())).execute();
    }

    private void createChildCategories(final CategoryEntity entity, final List<ChildCategory> childCategories) {
        if (CollectionUtils.isEmpty(childCategories)) {
            return;
        }
        for (ChildCategory childCategory : childCategories) {
            final ChildCategoryEntity childCategoryEntity = new ChildCategoryEntity();
            childCategoryEntity.setName(childCategory.getItems().getCategoryName());
            childCategoryEntity.setCategoryId(childCategory.getItems().getCategoryId());
            childCategoryEntity.setDescription(childCategory.getItems().getCategoryDescription());
            childCategoryEntity.setCategory(entity);

            childCategoryEntity.save();
        }
    }

    @Override
    public Category get(String id) {
        return get(CategoryEntity.COLUMN_CATEGORY_ID, id);
    }

}
