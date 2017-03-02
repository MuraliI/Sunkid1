package com.rcl.excalibur.adapters.delegate.factory;


import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

import java.util.List;

public interface DetailModuleFactory {

    SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray();

    List<RecyclerViewType> getListOfDetailViewTypes();
}
