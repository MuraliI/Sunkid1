package com.rcl.excalibur.data.entity;

import com.activeandroid.Model;

public class OfferingEntity extends Model {
    public String offeringId;
    public ProductEntity productEntity;
    public String date;
    public String time;
    public PriceEntity price;
}
