package com.rcl.excalibur.adapters.delegate;


import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.BaseCoordinatorAdapter;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

import java.util.List;

import io.reactivex.Observer;

public class DetailViewCoordinatorAdapter extends BaseCoordinatorAdapter {

    public DetailViewCoordinatorAdapter(Observer observer,
                                        SparseArrayCompat<DelegateAdapter> delegateAdapters,
                                        List<RecyclerViewType> recyclerViewTypes) {
        super(observer);
        this.delegateAdapters = delegateAdapters;
        addAll(recyclerViewTypes);
    }
}
