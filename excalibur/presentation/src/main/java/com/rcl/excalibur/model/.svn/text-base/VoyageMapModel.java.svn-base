package com.rcl.excalibur.model;

import android.graphics.PointF;

public class VoyageMapModel {
    private static final int CENTER_OFFSET = 200;
    private static final int DAYS_DIFFERENCE = 200;
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
        switch (c) {
            case '1':
                coordinate.set(midWidht - 3 * DAYS_DIFFERENCE, midHeight + 3 * DAYS_DIFFERENCE);
                break;
            case '2':
                coordinate.set(midWidht - 2 * DAYS_DIFFERENCE, midHeight + 2 * DAYS_DIFFERENCE);
                break;
            case '3':
                coordinate.set(midWidht - 1 * DAYS_DIFFERENCE, midHeight + 1 * DAYS_DIFFERENCE);
                break;
            case '4':
                coordinate.set(midWidht, midHeight);
                break;
            case '5':
                coordinate.set(midWidht + 1 * DAYS_DIFFERENCE, midHeight - 1 * DAYS_DIFFERENCE);
                break;
            case '6':
                coordinate.set(midWidht + 2 * DAYS_DIFFERENCE, midHeight - 2 * DAYS_DIFFERENCE);
                break;
            case '7':
                coordinate.set(midWidht + 3 * DAYS_DIFFERENCE, midHeight - 3 * DAYS_DIFFERENCE);
                break;
            case '8':
                coordinate.set(midWidht + 4 * DAYS_DIFFERENCE, midHeight - 4 * DAYS_DIFFERENCE);
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
