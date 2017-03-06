package com.rcl.excalibur.mapper;

import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.model.DiscoverItemModel;

public class DiscoverModelDataMapper extends BaseModelDataMapper<DiscoverItemModel, DiscoverItem> {

    public DiscoverModelDataMapper() {
    }

    @Override
    public DiscoverItemModel transform(DiscoverItem discoverItem) {
        if (discoverItem == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final DiscoverItemModel discoverModel = new DiscoverItemModel();
        discoverModel.setImageUrl(discoverItem.getImageUrl());
        discoverModel.setCategory(discoverItem.getCategory());
        discoverModel.setDiscoverId(discoverItem.getDiscoverId());
        discoverModel.setHours(discoverItem.getHours());
        discoverModel.setPromotionText(discoverItem.getPromotionText());
        discoverModel.setTitle(discoverItem.getTitle());
        discoverModel.setType(discoverItem.getType());

        return discoverModel;
    }
}
