package com.rcl.excalibur.domain;


import android.graphics.PointF;
import android.graphics.Region;

public class ProductDeckMapModel extends Product {
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
