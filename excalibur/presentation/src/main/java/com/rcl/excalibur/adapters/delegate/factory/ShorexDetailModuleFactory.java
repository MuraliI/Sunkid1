package com.rcl.excalibur.adapters.delegate.factory;

import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.ExpandableDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.ExpandableLinkDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PricesFromDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PromotionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.StandardTimesDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.TitleAndDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ExpandableDescriptionViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableLinkViewType;
import com.rcl.excalibur.adapters.viewtype.PricesFromViewType;
import com.rcl.excalibur.adapters.viewtype.PromotionViewType;
import com.rcl.excalibur.adapters.viewtype.StandardTimesViewType;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_DESCRIPTION;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_LINK;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_PRICES_FROM;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_PROMOTION;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_STANDARD_TIMES;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_TITLE_AND_DESCRIPTION;

class ShorexDetailModuleFactory extends DetailModuleFactory {
    private static final int POSITION_PRICE_ADULTS = 1;
    private static final int POSITION_PRICE_CHILDREN = 3;

    @Override
    public SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray() {
        SparseArrayCompat<DelegateAdapter> delegates = new SparseArrayCompat<>();
        delegates.append(VIEW_TYPE_PROMOTION, new PromotionDelegateAdapter());
        delegates.append(VIEW_TYPE_STANDARD_TIMES, new StandardTimesDelegateAdapter());
        delegates.append(VIEW_TYPE_PRICES_FROM, new PricesFromDelegateAdapter());
        delegates.append(VIEW_TYPE_TITLE_AND_DESCRIPTION, new TitleAndDescriptionDelegateAdapter());
        delegates.append(VIEW_TYPE_EXPANDABLE_DESCRIPTION, new ExpandableDescriptionDelegateAdapter());
        delegates.append(VIEW_TYPE_EXPANDABLE_LINK, new ExpandableLinkDelegateAdapter());
        return delegates;
    }

    @Override
    public List<RecyclerViewType> getListOfDetailViewTypes(Resources resources) {
        List<RecyclerViewType> types = new ArrayList<>();
        /*types.add(new PromotionViewType(product.getPromotionTitle(), product.getPromotionDescription()));
        types.add(new StandardTimesViewType(product.getStandardTimesTitle(), product.getStandardTimesDaysAndTimes()));
        types.add(new PricesFromViewType(
                product.getPriceRange()[POSITION_PRICE_ADULTS],
                product.getPriceRange()[POSITION_PRICE_CHILDREN]));
        addTitleAndDescriptionTypes(types);
        types.add(new ExpandableDescriptionViewType(product.getDescription()));

        types.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_accessibility),
                product.getAccessibility(),
                true));
        types.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_legal),
                new String[]{product.getLegal()},
                false));*/
        return types;
    }
}
