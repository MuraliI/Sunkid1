package com.rcl.excalibur.adapters.delegate.factory;


import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

import java.util.ArrayList;
import java.util.List;

public class DinningDetailModuleFactory implements DetailModuleFactory {
    private static final int VIEW_TYPES_COUNT = 10;

    @Override
    public SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray() {
        /*FIXME this will return the Array of Delegate adapters that will process the different view types (modules) that the detail
        will have*/
        return new SparseArrayCompat<>(VIEW_TYPES_COUNT);
    }

    @Override
    public List<RecyclerViewType> getListOfDetailViewTypes() {
        //FIXME this will return the list of view types (modules) that the detail view must render
        return new ArrayList<>();
    }
}
