package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.TitleAndDescriptionViewType;
import com.rcl.excalibur.model.DiscoverItemModel;

import java.util.List;
import java.util.Map;

public abstract class DetailModuleFactory {
    DiscoverItemModel itemModel;

    public void setItemModel(DiscoverItemModel itemModel) {
        this.itemModel = itemModel;
    }

    public abstract SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray();

    public abstract List<RecyclerViewType> getListOfDetailViewTypes(Resources resources);

    public void addTitleAndDescriptionTypes(List<RecyclerViewType> recyclerViewTypeList) {
        // Title and description modules
        if (itemModel.getProperties() != null && itemModel.getProperties().size() > 0) {
            Map<String, String> properties = itemModel.getProperties();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                recyclerViewTypeList.add(new TitleAndDescriptionViewType(entry.getKey(), entry.getValue()));
            }
        }
    }
}
