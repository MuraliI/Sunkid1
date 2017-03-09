package com.rcl.excalibur.adapters.delegate.factory;

import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.ExpandableDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.ExpandableLinkDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.PromotionDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.StandardTimesDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.TitleAndDescriptionDelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ExpandableDescriptionViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableLinkViewType;
import com.rcl.excalibur.adapters.viewtype.PromotionViewType;
import com.rcl.excalibur.adapters.viewtype.StandardTimesViewType;
import com.rcl.excalibur.adapters.viewtype.TitleAndDescriptionViewType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_DESCRIPTION;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_LINK;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_PROMOTION;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_STANDARD_TIMES;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_TITLE_AND_DESCRIPTION;

class ActivityDetailModuleFactory extends DetailModuleFactory {
    private static final int VIEW_TYPES_COUNT = 5;

    @Override
    public SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray() {
        SparseArrayCompat<DelegateAdapter> delegates = new SparseArrayCompat<>(VIEW_TYPES_COUNT);
        delegates.append(VIEW_TYPE_PROMOTION, new PromotionDelegateAdapter());
        delegates.append(VIEW_TYPE_STANDARD_TIMES, new StandardTimesDelegateAdapter());
        delegates.append(VIEW_TYPE_TITLE_AND_DESCRIPTION, new TitleAndDescriptionDelegateAdapter());
        delegates.append(VIEW_TYPE_EXPANDABLE_DESCRIPTION, new ExpandableDescriptionDelegateAdapter());
        delegates.append(VIEW_TYPE_EXPANDABLE_LINK, new ExpandableLinkDelegateAdapter());
        return delegates;
    }

    @Override
    public List<RecyclerViewType> getListOfDetailViewTypes(Resources resources) {
        List<RecyclerViewType> types = new ArrayList<>();

        //Promotion text
        types.add(new PromotionViewType(itemModel.getPromotionTitle(), itemModel.getPromotionDescription()));

        //Standard times
        types.add(new StandardTimesViewType(itemModel.getStandardTimesTitle(), itemModel.getStandardTimesDaysAndTimes()));

        // Title and description modules
        if (itemModel.getProperties() != null && itemModel.getProperties().size() > 0) {
            Map<String, String> properties = itemModel.getProperties();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                types.add(new TitleAndDescriptionViewType(entry.getKey(), entry.getValue()));
            }
        }

        // Description module
        types.add(new ExpandableDescriptionViewType(itemModel.getDescription()));

        //Accessibility
        types.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_accessibility),
                itemModel.getAccessibility()));

        //Legal
        types.add(new ExpandableLinkViewType(
                resources.getString(R.string.detail_module_legal),
                itemModel.getLegal()));

        return types;
    }
}
