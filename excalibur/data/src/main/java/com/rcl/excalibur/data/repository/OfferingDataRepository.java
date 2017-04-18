package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.entity.PriceEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.mapper.OfferingDataMapper;
import com.rcl.excalibur.data.mapper.OfferingEntityDataMapper;
import com.rcl.excalibur.data.mapper.PriceDataMapper;
import com.rcl.excalibur.data.mapper.ProductEntityDataMapper;
import com.rcl.excalibur.data.utils.DBUtil;
import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.OfferingRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OfferingDataRepository extends BaseDataRepository<Offering, OfferingEntity, Void, OfferingDataMapper>
        implements OfferingRepository {

    /*FIXME this can be in the base class, because all of the repositories should have a mapper to create an entry in the database*/
    private OfferingEntityDataMapper entityDataMapper;

    public OfferingDataRepository() {
        super(new OfferingDataMapper(new PriceDataMapper(), new ProductEntityDataMapper()), OfferingEntity.class);
        entityDataMapper = new OfferingEntityDataMapper();
    }

    @Override
    public void create(@NonNull Offering input) {
        ProductEntity product = new Select()
                .from(ProductEntity.class)
                .where(DBUtil.eq(ProductEntity.COLUMN_PRODUCT_ID, input.getProduct() != null ? input.getProduct().getProductId() : ""))
                .executeSingle();
        OfferingEntity offering = entityDataMapper.transform(input, product);
        OfferingEntity dbOffering = new Select()
                .from(OfferingEntity.class)
                .where(DBUtil.and(DBUtil.eq(OfferingEntity.COLUMN_PRODUCT, product.getId())
                        , DBUtil.eq(OfferingEntity.COLUMN_DATE, offering.getDate())))
                .executeSingle();
        if (dbOffering == null) {
            offering.getPrice().save();
            offering.save();
        }
    }

    @Override
    public void deleteAll() {
        new Delete().from(OfferingEntity.class).execute();
        new Delete().from(PriceEntity.class).execute();
    }

    @Override
    public void getForDay(Date date, Observer<List<Offering>> observer) {
        Observable.create((ObservableOnSubscribe<List<Offering>>) e -> {
            SimpleDateFormat dateFormat = DateUtil.getHourlessDateParser();

            List<OfferingEntity> offerings = new Select()
                    .from(OfferingEntity.class)
                    .where(DBUtil.eq(OfferingEntity.COLUMN_DATE, dateFormat.format(date)))
                    .execute();

            e.onNext(getMapper().transform(offerings, null));
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getOfferingForProduct(Product product, Observer<List<Offering>> observer) {
        Observable.create((ObservableOnSubscribe<List<Offering>>) e -> {
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

            e.onNext(offeringList);
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
