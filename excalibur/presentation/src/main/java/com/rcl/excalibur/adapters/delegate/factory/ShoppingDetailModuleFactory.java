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
import com.rcl.excalibur.adapters.viewtype.ExpandableDescriptionViewType;
import com.rcl.excalibur.adapters.viewtype.PricesFromViewType;
import com.rcl.excalibur.utils.StringUtils;

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
        List<RecyclerViewType> types = new ArrayList<>();
        if (product.getStartingFromPrice() != null) {
            if (product.getStartingFromPrice().getAdultPrice() != 0 || product.getStartingFromPrice().getChildPrice() != 0) {
                types.add(new PricesFromViewType(StringUtils.getPriceFormated(product.getStartingFromPrice().getAdultPrice()),
                        StringUtils.getPriceFormated(product.getStartingFromPrice().getChildPrice())));
            }
        }
        addTitleAndDescriptionTypes(types);
        types.add(new ExpandableDescriptionViewType(product.getProductLongDescription()));
        return types;
    }
}
