package com.rcl.excalibur.data.entity.mapper;


import com.rcl.excalibur.data.entity.ItemEntity;
import com.rcl.excalibur.domain.Item;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link ItemEntity} (in the data layer) to {@link Item} in the
 * domain layer.
 */
@Singleton
public class ItemEntityDataMapper extends BaseEntityDataMapper<Item, ItemEntity> {

    @Inject
    ItemEntityDataMapper() {
    }

    @Override
    public Item transform(ItemEntity entity) {
        Item item = null;
        if (entity != null) {
            item = new Item();
            item.setName(entity.getName());
            item.setImageUrl(entity.getImageUrl());
        }
        return item;
    }
}
