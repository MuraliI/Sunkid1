package com.rcl.excalibur.data.repository;


import com.rcl.excalibur.data.entity.PromotionEntity;
import com.rcl.excalibur.data.mapper.PromotionEntityDataMapper;
import com.rcl.excalibur.domain.Promotion;
import com.rcl.excalibur.domain.repository.PromotionRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PromotionDataRepository extends BaseDataRepository<Promotion, PromotionEntity> implements PromotionRepository {

    private final PromotionEntityDataMapper promotionEntityDataMapper;

    @Inject
    PromotionDataRepository(PromotionEntityDataMapper promotionEntityDataMapper) {
        super(promotionEntityDataMapper, PromotionEntity.class);
        this.promotionEntityDataMapper = promotionEntityDataMapper;
    }

    @Override
    public void create(Promotion promotion) {
        final PromotionEntity entity = new PromotionEntity();
        entity.setCategoryId(promotion.getCategoryId());
        entity.setDescription(promotion.getDescription());
        entity.setLocationCode(promotion.getLocationCode());
        entity.setTitle(promotion.getTitle());
        entity.setProducts(promotion.getProducts());
        entity.save();
    }

    @Override
    public Promotion get(long id) {
        return get(PromotionEntity.COLUMN_CATEGORY_ID, id);
    }

}
