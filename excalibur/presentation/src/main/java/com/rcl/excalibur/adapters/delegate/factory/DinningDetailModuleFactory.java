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
        return new SparseArrayCompat<>(VIEW_TYPES_COUNT);
    }

    @Override
    public List<RecyclerViewType> getListOfDetailModules() {
        return new ArrayList<>();
    }
}
