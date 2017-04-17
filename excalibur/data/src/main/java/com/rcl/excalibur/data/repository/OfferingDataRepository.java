package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.mapper.OfferingDataMapper;
import com.rcl.excalibur.data.mapper.OfferingEntityDataMapper;
import com.rcl.excalibur.data.utils.DBUtil;
import com.rcl.excalibur.domain.Offering;

public class OfferingDataRepository extends BaseDataRepository<Offering, OfferingEntity, OfferingDataMapper> {

    /*FIXME this can be in the base class, because all of the repositories should have a mapper to create an entry in the database*/
    private OfferingEntityDataMapper entityDataMapper;

    public OfferingDataRepository() {
        super(new OfferingDataMapper(), OfferingEntity.class);
        entityDataMapper = new OfferingEntityDataMapper();
    }

    @Override
    public void create(@NonNull Offering input) {
        ProductEntity product = new Select()
                .from(ProductEntity.class)
                .where(DBUtil.eq(ProductEntity.COLUMN_PRODUCT_ID, input.getProductId()))
                .executeSingle();
        entityDataMapper.setProductEntity(product);
        OfferingEntity offering = entityDataMapper.transform(input);
        if (offering != null) {
            offering.getPrice().save();
            offering.save();
        }
    }

    @Override
    public void deleteAll() {
        //TODO implement delete all offerings
    }
}
