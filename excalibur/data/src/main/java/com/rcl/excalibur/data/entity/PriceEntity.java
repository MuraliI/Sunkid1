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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(String adultPrice) {
        this.adultPrice = adultPrice;
    }

    public String getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(String childPrice) {
        this.childPrice = childPrice;
    }

    public String getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(String infantPrice) {
        this.infantPrice = infantPrice;
    }
}
