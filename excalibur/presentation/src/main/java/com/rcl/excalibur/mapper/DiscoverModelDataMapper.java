package com.rcl.excalibur.mapper;

import com.rcl.excalibur.domain.Discover;
import com.rcl.excalibur.model.DiscoverModel;

public class DiscoverModelDataMapper extends BaseModelDataMapper<DiscoverModel, Discover> {

    public DiscoverModelDataMapper() {
    }

    @Override
    public DiscoverModel transform(Discover discover) {
        if (discover == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final DiscoverModel discoverModel = new DiscoverModel();
        discoverModel.setImageUrl(discover.getImageUrl());
        discoverModel.setCategory(discover.getCategory());
        discoverModel.setDiscoverId(discover.getDiscoverId());
        discoverModel.setHours(discover.getHours());
        discoverModel.setPromotionTextActitity(discover.getPromotionTextActitity());
        discoverModel.setTitle(discover.getTitle());
        discoverModel.setType(discover.getType());

        return discoverModel;
    }
}
