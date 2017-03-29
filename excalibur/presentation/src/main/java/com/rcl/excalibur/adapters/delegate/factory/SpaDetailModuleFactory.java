package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.ExpandableDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.ExpandableLinkDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PricesFromDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PromotionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.StandardTimesDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.TitleAndDescriptionDelegateAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_PRICES_FROM;

class SpaDetailModuleFactory extends DetailModuleFactory {

    @Override
    public SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray() {
        SparseArrayCompat<DelegateAdapter> arrayCompat = new SparseArrayCompat<>();
        arrayCompat.append(RecyclerViewConstants.VIEW_TYPE_PROMOTION, new PromotionDelegateAdapter());
        arrayCompat.append(RecyclerViewConstants.VIEW_TYPE_STANDARD_TIMES, new StandardTimesDelegateAdapter());
        arrayCompat.append(RecyclerViewConstants.VIEW_TYPE_TITLE_AND_DESCRIPTION, new TitleAndDescriptionDelegateAdapter());
        arrayCompat.append(RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_DESCRIPTION, new ExpandableDescriptionDelegateAdapter());
        arrayCompat.append(RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_LINK, new ExpandableLinkDelegateAdapter());
        arrayCompat.append(VIEW_TYPE_PRICES_FROM, new PricesFromDelegateAdapter());
        return arrayCompat;
    }

    @Override
    public List<RecyclerViewType> getListOfDetailViewTypes(Resources resources) {
        final List<RecyclerViewType> types = new ArrayList<>();
        addProductLocationTypes(types, resources);
        addLongDescriptionTypes(types);
        addPriceFromTypes(types);
        addProductDurationTypes(types, resources, R.string.sessions_from);
        return types;
    }
}
