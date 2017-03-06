package com.rcl.excalibur.data.entity.mapper;


import com.rcl.excalibur.data.entity.DiscoverItemEntity;
import com.rcl.excalibur.domain.DiscoverItem;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link DiscoverItemEntity} (in the data layer) to {@link DiscoverItem} in the
 * domain layer.
 */
@Singleton
public class DiscoverEntityDataMapper extends BaseEntityDataMapper<DiscoverItem, DiscoverItemEntity> {

    @Inject
    DiscoverEntityDataMapper() {
    }

    @Override
    public DiscoverItem transform(DiscoverItemEntity entity) {
        DiscoverItem discoverItem = null;
        if (entity != null) {
            discoverItem = new DiscoverItem();
            discoverItem.setImageUrl(entity.getImageUrl());
            discoverItem.setCategory(entity.getCategory());
            discoverItem.setDiscoverId(entity.getDiscoverItemId());
            discoverItem.setHours(entity.getHours());
            discoverItem.setPromotionText(entity.getPromotionText());
            discoverItem.setTitle(entity.getTitle());
            discoverItem.setType(entity.getType());
        }
        return discoverItem;
    }
}
