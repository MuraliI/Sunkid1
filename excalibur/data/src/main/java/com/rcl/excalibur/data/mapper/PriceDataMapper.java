package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.PriceEntity;
import com.rcl.excalibur.domain.SellingPrice;

public class PriceDataMapper extends BaseDataMapper<SellingPrice, PriceEntity, Void> {
    @Nullable
    @Override
    public SellingPrice transform(PriceEntity input, Void additionalArg) {
        SellingPrice output = new SellingPrice();
        output.setCurrency(input.getCurrency());
        output.setAdultPrice(Float.valueOf(input.getAdultPrice()));
        output.setChildPrice(Float.valueOf(input.getChildPrice()));
        output.setInfantPrice(Float.valueOf(input.getInfantPrice()));
        return output;
    }
}
