package com.rcl.excalibur.model;

import android.graphics.PointF;

public class VoyageMapModel {
    private static final int CENTER_OFFSET = 135;

    private int width;
    private int heigth;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public PointF getMockCoordinate(char c, boolean hasOffset) {
        PointF coordinate = new PointF();
        int midWidht = width / 2;
        int midHeight = heigth / 2;
        int differenceDays = 100;
        switch (c) {
            case '1':
                coordinate.set(midWidht - 3 * differenceDays, midHeight);
                break;
            case '2':
                coordinate.set(midWidht - 2 * differenceDays, midHeight);
                break;
            case '3':
                coordinate.set(midWidht - 1 * differenceDays, midHeight);
                break;
            case '4':
                coordinate.set(midWidht, midHeight);
                break;
            case '5':
                coordinate.set(midWidht + 1 * differenceDays, midHeight);
                break;
            case '6':
                coordinate.set(midWidht + 2 * differenceDays, midHeight);
                break;
            case '7':
                coordinate.set(midWidht + 3 * differenceDays, midHeight);
                break;
            case '8':
                coordinate.set(midWidht + 4 * differenceDays, midHeight);
                break;
            default:
                coordinate.set(midWidht, midHeight);
                break;
        }

        if (hasOffset) {
            coordinate.y = coordinate.y + CENTER_OFFSET;
        }

        return coordinate;
    }
}
