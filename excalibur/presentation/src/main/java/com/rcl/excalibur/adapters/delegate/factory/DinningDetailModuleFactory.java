package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.DinnerTimesDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.ExpandableLinkDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.ExpandableDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PriceRangeDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PromotionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.TitleAndDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.DinnerTimesViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableLinkViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableDescriptionViewType;
import com.rcl.excalibur.adapters.viewtype.PriceRangeViewType;
import com.rcl.excalibur.adapters.viewtype.PromotionViewType;
import com.rcl.excalibur.adapters.viewtype.TitleAndDescriptionViewType;
import com.rcl.excalibur.model.DiscoverItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DinningDetailModuleFactory implements DetailModuleFactory {
    private static final int VIEW_TYPES_COUNT_DINNING = 4;
    private DiscoverItemModel itemModel;

    public DinningDetailModuleFactory(DiscoverItemModel itemModel) {
        this.itemModel = itemModel;
    }

    @Override
    public SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray() {
        SparseArrayCompat<DelegateAdapter> delegateAdapters = null;
        if ("Dining".equals(itemModel.getType())) {
            delegateAdapters = new SparseArrayCompat<>(VIEW_TYPES_COUNT_DINNING);
            delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_TITLE_AND_DESCRIPTION, new TitleAndDescriptionDelegateAdapter());
            delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_DESCRIPTION, new ExpandableDescriptionDelegateAdapter());
            delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_LINK, new ExpandableLinkDelegateAdapter());
            delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_DINNER_TIMES, new DinnerTimesDelegateAdapter());
            delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_PRICE_RANGE, new PriceRangeDelegateAdapter());
            delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_PROMOTION, new PromotionDelegateAdapter());
        }
        return delegateAdapters;
    }

    @Override
    public List<RecyclerViewType> getListOfDetailViewTypes(Resources resources) {
        List<RecyclerViewType> viewTypes = new ArrayList<>();

        //Promotion text
        viewTypes.add(new PromotionViewType(itemModel.getPromotionTitle(), itemModel.getPromotionText()));

        //Dinner times
        //FIXME get this attributes from model
        viewTypes.add(new DinnerTimesViewType(
                resources.getString(R.string.hardcoded_lunch_time_description),
                null,
                resources.getString(R.string.hardcoded_dinner_time_description),
                null)
        );

        // Price Range
        viewTypes.add(new PriceRangeViewType(Integer.valueOf(itemModel.getPriceRange()[0]))); //FIXME get this attributes from model

        // Title and description modules
        if (itemModel.getProperties() != null && itemModel.getProperties().size() > 0) {
            Map<String, String> properties = itemModel.getProperties();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                viewTypes.add(new TitleAndDescriptionViewType(entry.getKey(), entry.getValue()));
            }
        }

        // Description module
        viewTypes.add(new ExpandableDescriptionViewType(itemModel.getDescription()));

        //Accessibility
        viewTypes.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_accessibility),
                itemModel.getAccessibility()));

        //Legal
        viewTypes.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_legal),
                itemModel.getLegal()));


        return viewTypes;
    }
}
