package com.rcl.excalibur.adapters.itinerary;

import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.BaseCoordinatorAdapter;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.delegate.itinerary.GreetingsDelegateAdapter;

import io.reactivex.Observer;


public class ItineraryCoodinatorAdapter extends BaseCoordinatorAdapter {

    private static final int VIEW_TYPE_COUNT = 3;

    public ItineraryCoodinatorAdapter(Observer observer) {
        super(observer);
        delegateAdapters = new SparseArrayCompat<DelegateAdapter>(VIEW_TYPE_COUNT);
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_GREETINGS, new GreetingsDelegateAdapter());
    }
}
