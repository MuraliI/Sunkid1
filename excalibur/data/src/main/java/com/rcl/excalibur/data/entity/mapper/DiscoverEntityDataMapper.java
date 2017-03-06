package com.rcl.excalibur.data.entity.mapper;


import com.rcl.excalibur.data.entity.DiscoverEntity;
import com.rcl.excalibur.domain.Discover;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link DiscoverEntity} (in the data layer) to {@link Discover} in the
 * domain layer.
 */
@Singleton
public class DiscoverEntityDataMapper extends BaseEntityDataMapper<Discover, DiscoverEntity> {

    @Inject
    DiscoverEntityDataMapper() {
    }

    @Override
    public Discover transform(DiscoverEntity entity) {
        Discover discover = null;
        if (entity != null) {
            discover = new Discover();
            discover.setImageUrl(entity.getImageUrl());
            discover.setCategory(entity.getCategory());
            discover.setDiscoverId(entity.getDiscoverId());
            discover.setHours(entity.getHours());
            discover.setPromotionTextActitity(entity.getPromotionTextActitity());
            discover.setTitle(entity.getTitle());
            discover.setType(entity.getType());
        }
        return discover;
    }
}
