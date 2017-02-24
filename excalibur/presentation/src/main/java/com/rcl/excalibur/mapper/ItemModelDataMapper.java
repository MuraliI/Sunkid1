package com.rcl.excalibur.mapper;

import com.rcl.excalibur.domain.Item;
import com.rcl.excalibur.internal.di.PerActivity;
import com.rcl.excalibur.model.ItemModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@PerActivity
public class ItemModelDataMapper {

    @Inject
    public ItemModelDataMapper() {
    }

    public ItemModel transform(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final ItemModel itemModel = new ItemModel();
        itemModel.setName(item.getName());
        itemModel.setImageUrl(item.getImageUrl());

        return itemModel;
    }


    public Collection<ItemModel> transform(Collection<Item> itemsCollection) {
        Collection<ItemModel> itemModelsCollection;

        if (itemsCollection != null && !itemsCollection.isEmpty()) {
            itemModelsCollection = new ArrayList<>();
            for (Item item : itemsCollection) {
                itemModelsCollection.add(transform(item));
            }
        } else {
            itemModelsCollection = Collections.emptyList();
        }

        return itemModelsCollection;
    }
}
