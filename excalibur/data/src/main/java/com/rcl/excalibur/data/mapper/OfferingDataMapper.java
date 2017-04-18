package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.domain.Offering;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import timber.log.Timber;

import static com.rcl.excalibur.data.utils.DateUtil.getStandardDateParser;

public class OfferingDataMapper extends BaseDataMapper<Offering, OfferingEntity> {

    private final PriceDataMapper priceDataMapper;
    private final SimpleDateFormat simpleDateFormat;

    public OfferingDataMapper() {
        priceDataMapper = new PriceDataMapper();
        simpleDateFormat = getStandardDateParser();
    }

    @Nullable
    @Override
    public Offering transform(OfferingEntity input) {
        Offering output = new Offering();
        output.setId(input.getOfferingId());
        output.setProductId(input.getProductEntity().getProductId());
        output.setPrice(priceDataMapper.transform(input.getPrice()));
        try {
            output.setDate(simpleDateFormat.parse(input.getDate()));
        } catch (ParseException e) {
            Timber.e(DateUtil.DATE_PARSING_ERROR, this.getClass().toString(), e.getMessage());
        }
        return output;
    }
}
