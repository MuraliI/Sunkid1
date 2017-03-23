package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.TitleAndDescriptionViewType;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductPreference;

import java.util.List;

public abstract class DetailModuleFactory {
    private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }

    public abstract SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray();

    public abstract List<RecyclerViewType> getListOfDetailViewTypes(Resources resources);

    void addTitleAndDescriptionTypes(List<RecyclerViewType> recyclerViewTypeList) {
        // Title and description modules
        if (product.getPreferences() != null && product.getPreferences().size() > 0) {
            List<ProductPreference> properties = product.getPreferences();
            for (ProductPreference productPreference : properties) {
                recyclerViewTypeList.add(new TitleAndDescriptionViewType(productPreference.getPreferenceName(), productPreference.getPreferenceType()));
            }
        }
    }
}
