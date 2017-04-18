package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.entity.DiscoverItemEntity;
import com.rcl.excalibur.domain.DiscoverItem;

/**
 * Mapper class used to transform {@link DiscoverItemEntity} (in the data layer) to {@link DiscoverItem} in the
 * domain layer.
 */
public class DiscoverEntityDataMapper extends BaseDataMapper<DiscoverItem, DiscoverItemEntity> {

    @Override
    public DiscoverItem transform(DiscoverItemEntity entity, Object... additionalArgs) {
        DiscoverItem discoverItem = null;
        if (entity != null) {
            discoverItem = new DiscoverItem();
            discoverItem.setDiscoverItemId(entity.getDiscoverItemId());
            discoverItem.setImageUrl(entity.getImageUrl());
            discoverItem.setCategory(entity.getCategory());
            discoverItem.setHours(entity.getHours());
            discoverItem.setTitle(entity.getTitle());
            discoverItem.setType(entity.getType());
            discoverItem.setSubTitle(entity.getSubTitle());
            discoverItem.setReservationRequired(entity.getReservationRequired());
            discoverItem.setDescription(entity.getDescription());
            discoverItem.setPromotionTitle(entity.getPromotionTitle());
            discoverItem.setPromotionDescription(entity.getPromotionDescription());
        }
        return discoverItem;
    }

}
