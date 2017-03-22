package com.rcl.excalibur.adapters.itinerary;

import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.BaseCoordinatorAdapter;

import io.reactivex.Observer;


public class ItineraryCoodinatorAdapter extends BaseCoordinatorAdapter {

    private static final int VIEW_TYPE_COUNT = 3;

    public ItineraryCoodinatorAdapter(Observer observer) {
        super(observer);
        delegateAdapters = new SparseArrayCompat(VIEW_TYPE_COUNT);
    }
}
