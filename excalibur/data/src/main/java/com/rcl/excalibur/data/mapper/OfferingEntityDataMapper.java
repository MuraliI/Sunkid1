package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.domain.Offering;

import java.text.SimpleDateFormat;

import static com.rcl.excalibur.data.utils.DateUtil.getHourlessDateParser;

public class OfferingEntityDataMapper extends BaseDataMapper<OfferingEntity, Offering, ProductEntity> {

    private final SimpleDateFormat simpleDateFormat;

    private PriceEntityDataMapper priceEntityDataMapper;

    public OfferingEntityDataMapper(PriceEntityDataMapper priceEntityDataMapper) {
        simpleDateFormat = getHourlessDateParser();
        this.priceEntityDataMapper = priceEntityDataMapper;
    }

    @Nullable
    @Override
    public OfferingEntity transform(Offering input, ProductEntity productEntity) {
        OfferingEntity offeringEntity = new OfferingEntity();
        offeringEntity.setOfferingId(input.getId());
        offeringEntity.setProductEntity(productEntity);
        offeringEntity.setDate(simpleDateFormat.format(input.getDate()));
        offeringEntity.setTime(DateUtil.getTime(input.getDate()));
        offeringEntity.setPrice(priceEntityDataMapper.transform(input.getPrice(), null));

        return offeringEntity;
    }
}
