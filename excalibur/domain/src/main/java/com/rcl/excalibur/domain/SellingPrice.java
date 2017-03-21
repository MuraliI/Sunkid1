package com.rcl.excalibur.domain;



public class SellingPrice {
    private String currency;
    private float adultPrice;
    private float childPrice;
    private float infantPrice;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(float adultPrice) {
        this.adultPrice = adultPrice;
    }

    public float getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(float childPrice) {
        this.childPrice = childPrice;
    }

    public float getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(float infantPrice) {
        this.infantPrice = infantPrice;
    }
}
