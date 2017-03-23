package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.entity.PromotionEntity;
import com.rcl.excalibur.domain.Promotion;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link PromotionEntity} (in the data layer) to {@link Promotion} in the
 * domain layer.
 */
@Singleton
public class PromotionEntityDataMapper extends BaseDataMapper<Promotion, PromotionEntity> {

    @Inject
    PromotionEntityDataMapper() {
    }

    @Override
    public Promotion transform(final PromotionEntity entity) {
        if (entity == null) {
            return null;
        }
        final Promotion promotion = new Promotion();
        promotion.setCategoryId(entity.getCategoryId());
        promotion.setDescription(entity.getDescription());
        promotion.setLocationCode(entity.getLocationCode());
        promotion.setTitle(entity.getTitle());
        promotion.setProducts(Arrays.asList(entity.getProducts()));

        return promotion;
    }
}
