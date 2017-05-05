package com.rcl.excalibur.data.mapper;

import android.support.annotation.NonNull;

import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.entity.ChildCategoryEntity;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.ChildCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryEntityDataMapper extends BaseDataMapper<Category, CategoryEntity, Void> {

    @Override
    public Category transform(@NonNull CategoryEntity model, Void additionalArg) {
        Category category = null;
        if (model != null) {
            category = new Category();
            category.setCategoryId(model.getCategoryId());
            category.setDescription(model.getDescription());
            category.setName(model.getName());
            category.setChildCategory(transformChildCategory(model.getChildCategory()));
        }
        return category;
    }

    private List<ChildCategory> transformChildCategory(List<ChildCategoryEntity> entities) {
        ArrayList<ChildCategory> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(entities)) {
            return items;
        }
        for (ChildCategoryEntity childCategoryEntity : entities) {
            if (childCategoryEntity == null) {
                continue;
            }

            ChildCategory childCategory = new ChildCategory();
            childCategory.getItems().setCategoryId(childCategoryEntity.getChildCategoryId());
            childCategory.getItems().setCategoryName(childCategoryEntity.getName());
            childCategory.getItems().setCategoryDescription(childCategoryEntity.getDescription());

            items.add(childCategory);
        }
        return items;
    }
}
