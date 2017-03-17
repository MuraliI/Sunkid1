package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.DinnerTimesDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.ExpandableDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.ExpandableLinkDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PriceRangeDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PromotionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.TitleAndDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.DinnerTimesViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableDescriptionViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableLinkViewType;
import com.rcl.excalibur.adapters.viewtype.PriceRangeViewType;
import com.rcl.excalibur.adapters.viewtype.PromotionViewType;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_DINNER_TIMES;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_DESCRIPTION;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_LINK;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_PRICE_RANGE;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_PROMOTION;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_TITLE_AND_DESCRIPTION;

class DinningDetailModuleFactory extends DetailModuleFactory {
    private static final int VIEW_TYPES_COUNT = 6;

    @Override
    public SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray() {
        SparseArrayCompat<DelegateAdapter> delegateAdapters = new SparseArrayCompat<>(VIEW_TYPES_COUNT);
        delegateAdapters.append(VIEW_TYPE_PROMOTION, new PromotionDelegateAdapter());
        delegateAdapters.append(VIEW_TYPE_DINNER_TIMES, new DinnerTimesDelegateAdapter());
        delegateAdapters.append(VIEW_TYPE_PRICE_RANGE, new PriceRangeDelegateAdapter());
        delegateAdapters.append(VIEW_TYPE_TITLE_AND_DESCRIPTION, new TitleAndDescriptionDelegateAdapter());
        delegateAdapters.append(VIEW_TYPE_EXPANDABLE_DESCRIPTION, new ExpandableDescriptionDelegateAdapter());
        delegateAdapters.append(VIEW_TYPE_EXPANDABLE_LINK, new ExpandableLinkDelegateAdapter());
        return delegateAdapters;
    }

    @Override
    public List<RecyclerViewType> getListOfDetailViewTypes(Resources resources) {
        List<RecyclerViewType> types = new ArrayList<>();
        types.add(new PromotionViewType(itemModel.getPromotionTitle(), itemModel.getPromotionDescription()));
        types.add(new DinnerTimesViewType(
                itemModel.getLunchTime(),
                itemModel.getLunchMenu(),
                itemModel.getDinnerTime(),
                itemModel.getDinnerMenu())
        );
        types.add(new PriceRangeViewType(Integer.valueOf(itemModel.getPriceRange()[0])));
        addTitleAndDescriptionTypes(types);
        types.add(new ExpandableDescriptionViewType(itemModel.getDescription()));
        types.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_accessibility),
                itemModel.getAccessibility(),
                true));
        types.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_legal),
                new String[]{itemModel.getLegal()},
                false));

        return types;
    }
}
