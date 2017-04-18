package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.SellingPriceResponse;
import com.rcl.excalibur.domain.SellingPrice;

public class PriceResponseMapper extends BaseDataMapper<SellingPrice, SellingPriceResponse> {

    @Nullable
    @Override
    public SellingPrice transform(SellingPriceResponse entity, Object... additionalArgs) {
        SellingPrice sellingPrice = new SellingPrice();
        sellingPrice.setCurrency(entity.getCurrency());
        sellingPrice.setAdultPrice(entity.getAdultPrice());
        sellingPrice.setChildPrice(entity.getChildPrice());
        sellingPrice.setInfantPrice(entity.getInfantPrice());

        return sellingPrice;
    }
}
