package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.entity.PriceEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.mapper.OfferingDataMapper;
import com.rcl.excalibur.data.mapper.PriceDataMapper;
import com.rcl.excalibur.data.mapper.ProductEntityDataMapper;
import com.rcl.excalibur.data.utils.DBUtil;
import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.OfferingRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferingDataRepository extends BaseDataRepository<Offering, OfferingEntity, Void, OfferingDataMapper>
        implements OfferingRepository {

    private static final String ORDER_BY_TITLE = "product.title";

    public OfferingDataRepository() {
        super(new OfferingDataMapper(new PriceDataMapper(), new ProductEntityDataMapper()), OfferingEntity.class);
    }

    @Override
    public void create(@NonNull Offering input) {
    }

    @Override
    public void deleteAll() {
        new Delete().from(OfferingEntity.class).execute();
        new Delete().from(PriceEntity.class).execute();
    }

    @Override
    public List<Offering> getForDay(Date date) {
        String dateFormatted = DateUtil.parseHourless(date);

        List<OfferingEntity> offerings = new Select()
                .from(OfferingEntity.class)
                .innerJoin(ProductEntity.class)
                .on(DBUtil.on(OfferingEntity.TABLE_NAME, OfferingEntity.COLUMN_PRODUCT, ProductEntity.TABLE_NAME))
                .where(DBUtil.eq(OfferingEntity.COLUMN_DATE, dateFormatted))
                .orderBy(ORDER_BY_TITLE)
                .execute();

        return getMapper().transform(offerings, null);
    }

    @Override
    public List<Offering> getOfferingsForProduct(Product product) {
        ProductEntity productEntity = new Select()
                .from(ProductEntity.class)
                .where(DBUtil.eq(ProductEntity.COLUMN_PRODUCT_ID, product.getProductId()))
                .executeSingle();

        List<Offering> offeringList = new ArrayList<>();

        if (productEntity != null) {
            List<OfferingEntity> offeringEntities = new Select()
                    .from(OfferingEntity.class)
                    .where(DBUtil.eq(OfferingEntity.COLUMN_PRODUCT), productEntity.getId())
                    .execute();
            offeringList.addAll(getMapper().transform(offeringEntities, null));
        }

        return offeringList;
    }
}
