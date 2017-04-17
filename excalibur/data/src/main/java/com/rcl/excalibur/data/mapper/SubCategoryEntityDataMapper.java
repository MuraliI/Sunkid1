package com.rcl.excalibur.data.mapper;


import android.support.annotation.NonNull;

import com.rcl.excalibur.data.entity.ChildCategoryEntity;
import com.rcl.excalibur.data.entity.SubCategoryEntity;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.ChildCategory;
import com.rcl.excalibur.domain.SubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class used to transform {@link SubCategoryEntity} (in the data layer) to {@link SubCategory} in the
 * domain layer.
 */
public class SubCategoryEntityDataMapper extends BaseDataMapper<SubCategory, SubCategoryEntity> {


    @Override
    public SubCategory transform(@NonNull SubCategoryEntity model) {
        SubCategory subCategory = null;
        if (model != null) {
            subCategory = new SubCategory();
            subCategory.setCategoryId(model.getCategoryId());
            subCategory.setCategoryDescription(model.getDescription());
            subCategory.setCategoryName(model.getName());
            subCategory.setChildCategory(transformChildCategory(model.getChildCategories()));
        }
        return subCategory;
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
            childCategory.getItems().setCategoryId(childCategoryEntity.getCategoryId());
            childCategory.getItems().setCategoryName(childCategoryEntity.getName());
            childCategory.getItems().setCategoryDescription(childCategoryEntity.getDescription());

            items.add(childCategory);
        }
        return items;
    }

}
