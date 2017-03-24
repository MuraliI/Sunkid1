package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.ExpandableDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.ExpandableLinkDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PricesFromDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PromotionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.StandardTimesDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.TitleAndDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ExpandableDescriptionViewType;
import com.rcl.excalibur.adapters.viewtype.PricesFromViewType;
import com.rcl.excalibur.adapters.viewtype.StandardTimesViewType;
import com.rcl.excalibur.utils.ProductPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_PRICES_FROM;

class SpaDetailModuleFactory extends DetailModuleFactory {
    private static final int VIEW_TYPES_COUNT = 6;
    private static final int FIRST_PRICE_POSITION = 0;
    private static final int SECOND_PRICE_POSITION = 1;

    @Override
    public SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray() {
        SparseArrayCompat<DelegateAdapter> arrayCompat = new SparseArrayCompat<>(VIEW_TYPES_COUNT);
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
        List<RecyclerViewType> types = new ArrayList<>();

        if (product.getProductLocation() != null) {
            ArrayList<String[]> arrayListTimes = new ArrayList<>();
            arrayListTimes.add(new String[]{"Day 1", product.getTimeFrame() });
            types.add(new StandardTimesViewType("Operating hours", arrayListTimes));
        }

        types.add(new ExpandableDescriptionViewType(product.getProductShortDescription()));

        if (product.getStartingFromPrice() != null) {
            types.add(new PricesFromViewType(product.getStartingFromPrice().getAdultPrice() + "",
                    product.getStartingFromPrice().getChildPrice() + ""));
        }

        ProductPreferencesUtils productPreferencesUtils = new ProductPreferencesUtils();
        //TODO Refactor this so ProductPreferenceUtils can manage all types and return the preferences according to the factory needed.
        if (product.getProductDuration() != null) {
            productPreferencesUtils.addProduct("Sessions from", product.getProductDuration().getDurationInMinutes() + " mins");
        }

        addTitleAndDescriptionTypes(types);

        return types;
    }
}
