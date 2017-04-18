package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.PriceEntity;
import com.rcl.excalibur.domain.SellingPrice;

public class PriceEntityDataMapper extends BaseDataMapper<PriceEntity, SellingPrice> {

    @Nullable
    @Override
    public PriceEntity transform(SellingPrice input, Object... additionalArgs) {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setCurrency(input.getCurrency());
        priceEntity.setAdultPrice(String.valueOf(input.getAdultPrice()));
        priceEntity.setChildPrice(String.valueOf(input.getChildPrice()));
        priceEntity.setInfantPrice(String.valueOf(input.getInfantPrice()));
        return priceEntity;
    }
}
