package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.domain.Offering;

import java.text.SimpleDateFormat;

import static com.rcl.excalibur.data.utils.DateUtil.getStandardDateParser;

public class OfferingEntityDataMapper extends BaseDataMapper<OfferingEntity, Offering> {

    private final SimpleDateFormat simpleDateFormat;

    private ProductEntity productEntity;
    private PriceEntityDataMapper priceEntityDataMapper;

    public OfferingEntityDataMapper() {
        simpleDateFormat = getStandardDateParser();
        priceEntityDataMapper = new PriceEntityDataMapper();
    }

    @Nullable
    @Override
    public OfferingEntity transform(Offering input) {
        OfferingEntity offeringEntity = new OfferingEntity();
        offeringEntity.setOfferingId(input.getId());
        offeringEntity.setProductEntity(productEntity);
        offeringEntity.setDate(simpleDateFormat.format(input.getDate()));
        offeringEntity.setPrice(priceEntityDataMapper.transform(input.getPrice()));

        return offeringEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
