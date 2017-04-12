package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = OfferingEntity.TABLE_NAME)
public class OfferingEntity extends Model {

    public static final String TABLE_NAME = "offering";

    public static final String COLUMN_OFFERING_ID = "offering_id";
    public static final String COLUMN_PRODUCT = "product";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PRICE = "price";

    @Column(name = COLUMN_OFFERING_ID)
    public String offeringId;
    @Column(name = COLUMN_PRODUCT)
    public ProductEntity productEntity;
    @Column(name = COLUMN_DATE)
    public String date;
    @Column(name = COLUMN_PRICE)
    public PriceEntity price;

    public String getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(String offeringId) {
        this.offeringId = offeringId;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }
}
