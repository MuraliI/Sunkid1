package com.rcl.excalibur.data.entity.mapper;


import com.rcl.excalibur.data.entity.ItemEntity;
import com.rcl.excalibur.domain.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link ItemEntity} (in the data layer) to {@link Item} in the
 * domain layer.
 */
@Singleton
public class ItemEntityDataMapper {

    @Inject
    ItemEntityDataMapper() {
    }


    public Item transform(ItemEntity entity) {
        Item item = null;
        if (entity != null) {
            item = new Item();
            item.setName(entity.getName());
            item.setImageUrl(entity.getImageUrl());
        }
        return item;
    }


    public List<Item> transform(Collection<ItemEntity> itemEntityCollection) {
        final List<Item> itemList = new ArrayList();
        for (ItemEntity itemEntity : itemEntityCollection) {
            final Item item = transform(itemEntity);
            if (item != null) {
                itemList.add(item);
            }
        }
        return itemList;
    }
}
