package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = StartingFromPriceEntity.TABLE_NAME)
public class StartingFromPriceEntity extends Model {

    public static final String TABLE_NAME = "starting_from_price";
    public static final String COLUMN_CURRENCY = "currency";
    public static final String COLUMN_ADULT_PRICE = "adult_price";
    public static final String COLUMN_CHILD_PRICE = "child_price";
    public static final String COLUMN_INFANT_PRICE = "infant_price";

    @Column(name = COLUMN_CURRENCY)
    private String currency;
    @Column(name = COLUMN_ADULT_PRICE)
    private long adultPrice;
    @Column(name = COLUMN_CHILD_PRICE)
    private long childPrice;
    @Column(name = COLUMN_INFANT_PRICE)
    private long infantPrice;

    public StartingFromPriceEntity() {
        super();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(long adultPrice) {
        this.adultPrice = adultPrice;
    }

    public long getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(long childPrice) {
        this.childPrice = childPrice;
    }

    public long getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(long infantPrice) {
        this.infantPrice = infantPrice;
    }
}
