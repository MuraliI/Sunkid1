package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.domain.Offering;

public class OfferingDataMapper extends BaseDataMapper<Offering, OfferingEntity, Void> {

    private final PriceDataMapper priceDataMapper;
    private final ProductEntityDataMapper productDataMapper;

    public OfferingDataMapper(PriceDataMapper priceDataMapper, ProductEntityDataMapper productDataMapper) {
        this.priceDataMapper = priceDataMapper;
        this.productDataMapper = productDataMapper;
    }

    @Nullable
    @Override
    public Offering transform(OfferingEntity input, Void additionalArg) {
        Offering output = new Offering();
        output.setId(input.getOfferingId());
        output.setProduct(productDataMapper.transform(input.getProductEntity(), null));
        output.setPrice(priceDataMapper.transform(input.getPrice(), null));
        output.setDate(DateUtil.parseDateResponse(input.getDate(), input.getTime()));
        output.setTimeString(input.getTime());
        output.setDateString(input.getDate());
        return output;
    }
}
