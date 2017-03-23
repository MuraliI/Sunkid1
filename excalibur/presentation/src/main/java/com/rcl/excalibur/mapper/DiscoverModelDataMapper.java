package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.model.DiscoverItemModel;

public class DiscoverModelDataMapper extends BaseModelDataMapper<DiscoverItem, DiscoverItemModel> {

    public DiscoverModelDataMapper() {
    }

    @NonNull
    @Override
    public DiscoverItemModel transform(DiscoverItem discoverItem) {
        if (discoverItem == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final DiscoverItemModel discoverItemModel = new DiscoverItemModel();
        discoverItemModel.setDiscoverId(discoverItem.getDiscoverItemId());
        discoverItemModel.setImageUrl(discoverItem.getImageUrl());
        discoverItemModel.setCategory(discoverItem.getCategory());
        discoverItemModel.setHours(discoverItem.getHours());
        discoverItemModel.setTitle(discoverItem.getTitle());
        discoverItemModel.setType(discoverItem.getType());
        //discoverItemModel.setSubTitle(discoverItem.getSubTitle());
        discoverItemModel.setDescription(discoverItem.getDescription());
        discoverItemModel.setPromotionTitle(discoverItem.getPromotionTitle());
        discoverItemModel.setPromotionDescription(discoverItem.getPromotionDescription());
        discoverItemModel.setLegal(discoverItem.getLegal());

        return discoverItemModel;
    }

}
