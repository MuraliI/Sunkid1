package com.rcl.excalibur.adapters.delegate.factory;

import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.DelegateAdapter;
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
import com.rcl.excalibur.utils.StringUtils;

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
        if (product.getStartingFromPrice() != null) {
            types.add(new PricesFromViewType(StringUtils.getPriceFormated(product.getStartingFromPrice().getAdultPrice()),
                    StringUtils.getPriceFormated(product.getStartingFromPrice().getChildPrice())));
        }

        ProductPreferencesUtils productPreferencesUtils = new ProductPreferencesUtils();
        //TODO Refactor this so ProductPreferenceUtils can manage all types and return the preferences according to the factory needed.
        if (product.getRestrictions() != null && product.getRestrictions().size() > 0) {
            productPreferencesUtils.addProduct("Ages", product.getRestrictions().get(0).getRestrictionDisplayText());
        }
        productPreferencesUtils.addProduct("Duration", product.getProductDuration().getDurationInMinutes() + " mins");

        //TODO remove hardcode lines
        if (product.getActivityLevel() != null) {
            String activityLevel = "";
            if (123 == product.getActivityLevel().getActivityLevelId()) {
                activityLevel = "Mild";
            } else if (903 == product.getActivityLevel().getActivityLevelId()) {
                activityLevel = "Moderate";
            }
            productPreferencesUtils.addProduct("Activity Level",
                    activityLevel);
        }
        product.setPreferences(productPreferencesUtils.getProperties());

        if (product.getProductLocation() != null) {
            ArrayList<String[]> arrayListTimes = new ArrayList<>();
            arrayListTimes.add(new String[]{"Day 1", product.getTimeFrame() });
            types.add(new StandardTimesViewType("Operating hours", arrayListTimes));
        }
        addTitleAndDescriptionTypes(types);
        types.add(new ExpandableDescriptionViewType(product.getProductShortDescription()));

        return types;
    }
}
