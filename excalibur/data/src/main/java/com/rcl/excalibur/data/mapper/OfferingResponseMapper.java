package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.OfferingResponse;
import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;

public class OfferingResponseMapper extends BaseDataMapper<Offering, OfferingResponse, Product> {

    private static final int TIME_SIZE = 4;
    private PriceResponseMapper priceResponseMapper;

    public OfferingResponseMapper(PriceResponseMapper priceResponseMapper) {
        this.priceResponseMapper = priceResponseMapper;
    }

    @Nullable
    @Override
    public Offering transform(OfferingResponse response, Product product) {
        Offering offering = new Offering();
        offering.setId(response.getOfferingId());
        offering.setProduct(product);
        offering.setPrice(priceResponseMapper.transform(response.getOfferingPrice(), null));
        //FIXME when service return right time format;
        String dateTime = response.getOfferingTime();
        while (dateTime.length() < TIME_SIZE) {
            dateTime = "0" + dateTime;
        }
        offering.setDate(DateUtil.parseDateResponse(response.getOfferingDate(), dateTime));
        offering.setDateString(response.getOfferingDate());
        offering.setTimeString(response.getOfferingTime());
        return offering;
    }
}
