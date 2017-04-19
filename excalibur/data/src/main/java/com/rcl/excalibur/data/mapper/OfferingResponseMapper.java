package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.OfferingResponse;
import com.rcl.excalibur.data.service.response.ProductResponse;
import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.domain.Offering;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import timber.log.Timber;

import static com.rcl.excalibur.data.utils.DateUtil.getStandardDateParser;

public class OfferingResponseMapper extends BaseDataMapper<Offering, OfferingResponse, ProductResponse> {

    private SimpleDateFormat sdf;
    private PriceResponseMapper priceResponseMapper;
    private ProductResponseDataMapper productResponseDataMapper;

    public OfferingResponseMapper(ProductResponseDataMapper productResponseDataMapper) {
        sdf = getStandardDateParser();
        priceResponseMapper = new PriceResponseMapper();
        this.productResponseDataMapper = productResponseDataMapper;
    }

    @Nullable
    @Override
    public Offering transform(OfferingResponse entity, ProductResponse productResponse) {
        Offering offering = new Offering();
        offering.setId(entity.getOfferingId());
        offering.setProduct(productResponseDataMapper.transform(productResponse, null));
        offering.setPrice(priceResponseMapper.transform(entity.getOfferingPrice(), null));
        try {
            offering.setDate(sdf.parse(entity.getOfferingDate() + entity.getOfferingTime()));
        } catch (ParseException e) {
            Timber.e(DateUtil.DATE_PARSING_ERROR, this.getClass().toString(), e.getMessage());
        }
        return offering;
    }
}
