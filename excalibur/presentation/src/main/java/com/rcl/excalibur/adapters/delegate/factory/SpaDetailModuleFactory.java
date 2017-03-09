package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.ExpandableDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.ExpandableLinkDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PriceRangeDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PromotionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.StandardTimesDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.TitleAndDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ExpandableDescriptionViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableLinkViewType;
import com.rcl.excalibur.adapters.viewtype.PriceRangeViewType;
import com.rcl.excalibur.adapters.viewtype.PromotionViewType;
import com.rcl.excalibur.adapters.viewtype.StandardTimesViewType;

import java.util.ArrayList;
import java.util.List;

public class SpaDetailModuleFactory extends DetailModuleFactory {
    private static final int VIEW_TYPES_COUNT = 6;
    private static final int PRICE_POSITION = 0;

    @Override
    public SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray() {
        SparseArrayCompat<DelegateAdapter> arrayCompat = new SparseArrayCompat<>(VIEW_TYPES_COUNT);
        arrayCompat.append(RecyclerViewConstants.VIEW_TYPE_PROMOTION, new PromotionDelegateAdapter());
        arrayCompat.append(RecyclerViewConstants.VIEW_TYPE_STANDARD_TIMES, new StandardTimesDelegateAdapter());
        arrayCompat.append(RecyclerViewConstants.VIEW_TYPE_TITLE_AND_DESCRIPTION, new TitleAndDescriptionDelegateAdapter());
        arrayCompat.append(RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_DESCRIPTION, new ExpandableDescriptionDelegateAdapter());
        arrayCompat.append(RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_LINK, new ExpandableLinkDelegateAdapter());
        arrayCompat.append(RecyclerViewConstants.VIEW_TYPE_PRICE_RANGE, new PriceRangeDelegateAdapter());
        return arrayCompat;
    }

    @Override
    public List<RecyclerViewType> getListOfDetailViewTypes(Resources resources) {
        List<RecyclerViewType> types = new ArrayList<>();
        types.add(new PromotionViewType(itemModel.getPromotionTitle(), itemModel.getPromotionDescription()));
        types.add(new StandardTimesViewType(itemModel.getStandardTimesTitle(), itemModel.getStandardTimesDaysAndTimes()));
        types.add(new PriceRangeViewType(Integer.valueOf(itemModel.getPriceRange()[PRICE_POSITION])));
        addTitleAndDescriptionTypes(types);
        types.add(new ExpandableDescriptionViewType(itemModel.getDescription()));
        types.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_accessibility),
                itemModel.getAccessibility()));
        types.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_legal),
                itemModel.getLegal()));
        return types;
    }
}
