package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.DinnerTimesDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.ExpandableLinkDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PriceRangeDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PromotionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.TitleAndDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.DinnerTimesViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableLinkViewType;
import com.rcl.excalibur.adapters.viewtype.PriceRangeViewType;
import com.rcl.excalibur.adapters.viewtype.PromotionViewType;
import com.rcl.excalibur.adapters.viewtype.TitleAndDescriptionViewType;
import com.rcl.excalibur.model.DiscoverItemModel;

import java.util.ArrayList;
import java.util.List;

public class DinningDetailModuleFactory implements DetailModuleFactory {
    private static final int VIEW_TYPES_COUNT = 1;
    private DiscoverItemModel itemModel;

    public DinningDetailModuleFactory(DiscoverItemModel itemModel) {
        this.itemModel = itemModel;
    }

    @Override
    public SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray() {
        SparseArrayCompat<DelegateAdapter> delegateAdapters = new SparseArrayCompat<>(VIEW_TYPES_COUNT);
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_TITLE_AND_DESCRIPTION, new TitleAndDescriptionDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_LINK, new ExpandableLinkDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_DINNER_TIMES, new DinnerTimesDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_PRICE_RANGE, new PriceRangeDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_PROMOTION, new PromotionDelegateAdapter());
        return delegateAdapters;
    }

    @Override
    public List<RecyclerViewType> getListOfDetailViewTypes(Resources resources) {
        //TODO get age restriction from model
        List<RecyclerViewType> viewTypes = new ArrayList<>();
        viewTypes.add(new TitleAndDescriptionViewType(
                resources.getString(R.string.discover_item_detail_age_restriction_title),
                "All ages")); //FIXME get this attributes from model
        viewTypes.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_accessibility),
                "Content")); //FIXME get this attributes from model
        viewTypes.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_legal),
                "Content")); //FIXME get this attributes from model

        //FIXME get this attributes from model
        viewTypes.add(new DinnerTimesViewType(
                resources.getString(R.string.hardcoded_lunch_time_description),
                null,
                resources.getString(R.string.hardcoded_dinner_time_description),
                null));
        viewTypes.add(new PromotionViewType("Promotional title long version", "Content"));
        viewTypes.add(new PriceRangeViewType(2)); //FIXME get this attributes from model

        return viewTypes;
    }
}
