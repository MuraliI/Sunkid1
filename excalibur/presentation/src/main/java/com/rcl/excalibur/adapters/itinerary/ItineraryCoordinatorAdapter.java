package com.rcl.excalibur.adapters.itinerary;

import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.BaseCoordinatorAdapter;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.delegate.itinerary.SeparatorDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.itinerary.GreetingsDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.itinerary.ItineraryProductDelegateAdapter;

import io.reactivex.Observer;


public class ItineraryCoordinatorAdapter extends BaseCoordinatorAdapter {

    private static final int VIEW_TYPE_COUNT = 3;

    public ItineraryCoordinatorAdapter(Observer observer) {
        super(observer);
        delegateAdapters = new SparseArrayCompat<DelegateAdapter>(VIEW_TYPE_COUNT);
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_GREETINGS, new GreetingsDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_ITINERARY_PRODUCT_VIEW, new ItineraryProductDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_CALENDAR_VIEW, new SeparatorDelegateAdapter());
    }
}
