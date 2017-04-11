package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.rcl.excalibur.domain.Offering;

@Table(name = PriceEntity.TABLE_NAME)
public class PriceEntity extends Model {
    public static final String TABLE_NAME = "price";

    public static final String COLUMN_OFFERING = "offering";
    public static final String COLUMN_PRODUCT = "product";
    public static final String COLUMN_CURRENCY = "currency";
    public static final String COLUMN_ADULT_PRICE = "adult_price";
    public static final String COLUMN_CHILD_PRICE = "child_price";
    public static final String COLUMN_INFANT_PRICE = "infant_price";

    @Column(name = COLUMN_OFFERING)
    public Offering offering;
    @Column(name = COLUMN_PRODUCT)
    public ProductEntity product;
    @Column(name = COLUMN_CURRENCY)
    public String currency;
    @Column(name = COLUMN_ADULT_PRICE)
    public String adultPrice;
    @Column(name = COLUMN_CHILD_PRICE)
    public String childPrice;
    @Column(name = COLUMN_INFANT_PRICE)
    public String infantPrice;

    
}
