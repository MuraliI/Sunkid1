package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.SellingPriceResponse;
import com.rcl.excalibur.domain.SellingPrice;

public class PriceResponseMapper extends BaseDataMapper<SellingPrice, SellingPriceResponse, Void> {

    @Nullable
    @Override
    public SellingPrice transform(SellingPriceResponse entity, Void additionalArg) {
        SellingPrice sellingPrice = new SellingPrice();
        sellingPrice.setCurrency(entity.getCurrency());
        sellingPrice.setAdultPrice(entity.getAdultPrice());
        sellingPrice.setChildPrice(entity.getChildPrice());
        sellingPrice.setInfantPrice(entity.getInfantPrice());

        return sellingPrice;
    }
}
