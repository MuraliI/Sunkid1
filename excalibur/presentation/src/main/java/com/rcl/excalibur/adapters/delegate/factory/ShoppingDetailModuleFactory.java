package com.rcl.excalibur.adapters.delegate.factory;

import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.ExpandableDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.ExpandableLinkDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PromotionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.StandardTimesDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.TitleAndDescriptionDelegateAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_DESCRIPTION;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_LINK;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_PROMOTION;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_STANDARD_TIMES;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_TITLE_AND_DESCRIPTION;

class ShoppingDetailModuleFactory extends DetailModuleFactory {

    @Override
    public SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray() {
        SparseArrayCompat<DelegateAdapter> delegateAdapters = new SparseArrayCompat<>();
        delegateAdapters.append(VIEW_TYPE_PROMOTION, new PromotionDelegateAdapter());
        delegateAdapters.append(VIEW_TYPE_STANDARD_TIMES, new StandardTimesDelegateAdapter());
        delegateAdapters.append(VIEW_TYPE_TITLE_AND_DESCRIPTION, new TitleAndDescriptionDelegateAdapter());
        delegateAdapters.append(VIEW_TYPE_EXPANDABLE_DESCRIPTION, new ExpandableDescriptionDelegateAdapter());
        delegateAdapters.append(VIEW_TYPE_EXPANDABLE_LINK, new ExpandableLinkDelegateAdapter());
        return delegateAdapters;
    }

    @Override
    public List<RecyclerViewType> getListOfDetailViewTypes(Resources resources) {
        final List<RecyclerViewType> types = new ArrayList<>();
        addPriceFromTypes(types);
        addLongDescriptionTypes(types);
        return types;
    }
}
