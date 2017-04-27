package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.OfferingResponse;
import com.rcl.excalibur.data.service.response.ProductResponse;
import com.rcl.excalibur.domain.Offering;

public class OfferingResponseMapper extends BaseDataMapper<Offering, OfferingResponse, ProductResponse> {

    private PriceResponseMapper priceResponseMapper;
    private ProductResponseDataMapper productResponseDataMapper;

    public OfferingResponseMapper(ProductResponseDataMapper productResponseDataMapper, PriceResponseMapper priceResponseMapper) {
        this.priceResponseMapper = priceResponseMapper;
        this.productResponseDataMapper = productResponseDataMapper;
    }

    @Nullable
    @Override
    public Offering transform(OfferingResponse offeringResponse, ProductResponse productResponse) {
        Offering offering = new Offering();
        offering.setId(offeringResponse.getOfferingId());
        offering.setProduct(productResponseDataMapper.transform(productResponse, null));
        offering.setPrice(priceResponseMapper.transform(offeringResponse.getOfferingPrice(), null));
        offering.setDate(offeringResponse.getOfferingDate());
        offering.setTime(offeringResponse.getOfferingTime());
        return offering;
    }
}
