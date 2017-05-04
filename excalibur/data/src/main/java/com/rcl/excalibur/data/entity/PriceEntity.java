package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = PriceEntity.TABLE_NAME)
public class PriceEntity extends Model {
    public static final String TABLE_NAME = "price";

    public static final String COLUMN_CURRENCY = "currency";
    public static final String COLUMN_ADULT_PRICE = "adult_price";
    public static final String COLUMN_CHILD_PRICE = "child_price";
    public static final String COLUMN_INFANT_PRICE = "infant_price";

    @Column(name = COLUMN_CURRENCY)
    public String currency;
    @Column(name = COLUMN_ADULT_PRICE)
    public String adultPrice;
    @Column(name = COLUMN_CHILD_PRICE)
    public String childPrice;
    @Column(name = COLUMN_INFANT_PRICE)
    public String infantPrice;

    public PriceEntity() {
        super();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getAdultPrice() {
        return adultPrice != null ? Float.parseFloat(adultPrice) : 0f;
    }

    public void setAdultPrice(float adultPrice) {
        this.adultPrice = String.valueOf(adultPrice);
    }

    public float getChildPrice() {
        return childPrice != null ? Float.parseFloat(childPrice) : 0f;
    }

    public void setChildPrice(float childPrice) {
        this.childPrice = String.valueOf(childPrice);
    }

    public float getInfantPrice() {
        return infantPrice != null ? Float.parseFloat(infantPrice) : 0f;

    }

    public void setInfantPrice(float infantPrice) {
        this.infantPrice = String.valueOf(infantPrice);
    }

}
