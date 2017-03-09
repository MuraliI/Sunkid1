package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.model.DiscoverItemModel;

import java.util.List;

public abstract class DetailModuleFactory {
    DiscoverItemModel itemModel;

    public void setItemModel(DiscoverItemModel itemModel) {
        this.itemModel = itemModel;
    }

    public abstract SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray();

    public abstract List<RecyclerViewType> getListOfDetailViewTypes(Resources resources);
}
