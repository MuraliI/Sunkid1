package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.domain.Offering;

public class OfferingEntityDataMapper extends BaseDataMapper<OfferingEntity, Offering, ProductEntity> {

    private PriceEntityDataMapper priceEntityDataMapper;

    public OfferingEntityDataMapper(PriceEntityDataMapper priceEntityDataMapper) {
        this.priceEntityDataMapper = priceEntityDataMapper;
    }

    @Nullable
    @Override
    public OfferingEntity transform(Offering input, ProductEntity productEntity) {
        OfferingEntity offeringEntity = new OfferingEntity();
        offeringEntity.setOfferingId(input.getId());
        offeringEntity.setProductEntity(productEntity);
        offeringEntity.setDate(input.getDate());
        offeringEntity.setTime(input.getTime());
        offeringEntity.setPrice(priceEntityDataMapper.transform(input.getPrice(), null));

        return offeringEntity;
    }
}
