package com.rcl.excalibur.model;


import android.graphics.PointF;
import android.graphics.Region;

import com.rcl.excalibur.model.itinerary.ProductModel;

public class ProductDeckMapModel extends ProductModel {
    private PointF coordinate;
    private Region region;

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
