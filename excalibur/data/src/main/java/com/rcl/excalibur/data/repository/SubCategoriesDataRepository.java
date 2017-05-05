package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.entity.ChildCategoryEntity;
import com.rcl.excalibur.data.entity.SubCategoryEntity;
import com.rcl.excalibur.data.mapper.SubCategoryEntityDataMapper;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.ChildCategory;
import com.rcl.excalibur.domain.SubCategory;
import com.rcl.excalibur.domain.repository.SubCategoryRepository;

import java.util.List;

public class SubCategoriesDataRepository extends BaseDataRepository<SubCategory, SubCategoryEntity, Void, SubCategoryEntityDataMapper>
        implements SubCategoryRepository {

    public SubCategoriesDataRepository() {
        super(new SubCategoryEntityDataMapper(), SubCategoryEntity.class);
    }

    @Override
    public void create(List<SubCategory> subCategories) {
        ActiveAndroid.beginTransaction();
        try {
            for (SubCategory subCategory : subCategories) {
                create(subCategory);
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    @Override
    public void create(@NonNull SubCategory category) {
        final CategoryEntity entity = new CategoryEntity();
        entity.setCategoryId(category.getCategoryId());
        entity.setDescription(category.getCategoryDescription());
        entity.setName(category.getCategoryName());
        entity.save();

        createChildCategories(entity, category.getChildCategory());
    }

    @Override
    public void deleteAll() {
        new Delete().from(ChildCategoryEntity.class).execute();
        new Delete().from(SubCategoryEntity.class).execute();
    }

    private void createChildCategories(final CategoryEntity entity, final List<ChildCategory> childCategories) {
        if (CollectionUtils.isEmpty(childCategories)) {
            return;
        }
        for (ChildCategory childCategory : childCategories) {
            final ChildCategoryEntity childCategoryEntity = new ChildCategoryEntity();
            childCategoryEntity.setName(childCategory.getItems().getCategoryName());
            childCategoryEntity.setChildCategoryId(childCategory.getItems().getCategoryId());
            childCategoryEntity.setDescription(childCategory.getItems().getCategoryDescription());
            childCategoryEntity.setCategory(entity);

            childCategoryEntity.save();
        }
    }

    @Override
    public SubCategory get(String id) {
        return get(SubCategoryEntity.COLUMN_SUB_CATEGORY_ID, id);
    }

}
