package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.PriceEntity;
import com.rcl.excalibur.domain.SellingPrice;

@Deprecated
public class PriceEntityDataMapper extends BaseDataMapper<PriceEntity, SellingPrice, Void> {

    @Nullable
    @Override
    public PriceEntity transform(SellingPrice input, Void additionalArg) {
        PriceEntity priceEntity = new PriceEntity();
        return priceEntity;
    }
}
