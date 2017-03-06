package com.rcl.excalibur.mapper;

import com.rcl.excalibur.domain.Item;
import com.rcl.excalibur.model.ItemModel;

public class ItemModelDataMapper extends BaseModelDataMapper<ItemModel, Item> {

    public ItemModelDataMapper() {
    }

    @Override
    public ItemModel transform(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final ItemModel itemModel = new ItemModel();
        itemModel.setName(item.getName());
        itemModel.setImageUrl(item.getImageUrl());

        return itemModel;
    }
}
