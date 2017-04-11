package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.rcl.excalibur.data.entity.PromotionEntity;
import com.rcl.excalibur.data.mapper.PromotionEntityDataMapper;
import com.rcl.excalibur.domain.Promotion;
import com.rcl.excalibur.domain.repository.PromotionRepository;

public class PromotionDataRepository extends BaseDataRepository<Promotion, PromotionEntity> implements PromotionRepository {

    public PromotionDataRepository() {
        super(new PromotionEntityDataMapper(), PromotionEntity.class);
    }

    @Override
    public void create(@NonNull Promotion promotion) {
        final PromotionEntity entity = new PromotionEntity();
        entity.setCategoryId(promotion.getCategoryId());
        entity.setDescription(promotion.getDescription());
        entity.setLocationCode(promotion.getLocationCode());
        entity.setTitle(promotion.getTitle());
        entity.setProducts(promotion.getProducts());
        entity.save();
    }

    @Override
    public Promotion get(String id) {
        return get(PromotionEntity.COLUMN_CATEGORY_ID, id);
    }

}
