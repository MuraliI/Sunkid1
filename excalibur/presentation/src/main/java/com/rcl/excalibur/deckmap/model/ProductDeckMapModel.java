package com.rcl.excalibur.deckmap.model;


import android.graphics.PointF;
import android.graphics.Region;

import com.rcl.excalibur.domain.Product;

public class ProductDeckMapModel {
    private Product product;
    private PointF coordinate;
    private Region region;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PointF getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(PointF coordinate) {
        this.coordinate = coordinate;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
