package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.domain.Offering;

import java.text.SimpleDateFormat;

import static com.rcl.excalibur.data.utils.DateUtil.getHourlessDateParser;

public class OfferingEntityDataMapper extends BaseDataMapper<OfferingEntity, Offering> {

    private final SimpleDateFormat simpleDateFormat;

    private PriceEntityDataMapper priceEntityDataMapper;

    public OfferingEntityDataMapper() {
        simpleDateFormat = getHourlessDateParser();
        priceEntityDataMapper = new PriceEntityDataMapper();
    }

    @Nullable
    @Override
    public OfferingEntity transform(Offering input, Object... additionalArgs) {
        OfferingEntity offeringEntity = new OfferingEntity();
        offeringEntity.setOfferingId(input.getId());
        offeringEntity.setProductEntity(additionalArgs != null ? (ProductEntity) additionalArgs[0] : null);
        offeringEntity.setDate(simpleDateFormat.format(input.getDate()));
        offeringEntity.setTime(DateUtil.getTime(input.getDate()));
        offeringEntity.setPrice(priceEntityDataMapper.transform(input.getPrice()));

        return offeringEntity;
    }
}
