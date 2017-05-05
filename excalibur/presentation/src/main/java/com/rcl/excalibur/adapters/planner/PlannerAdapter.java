package com.rcl.excalibur.adapters.planner;


import android.support.annotation.Nullable;

import com.rcl.excalibur.adapters.planner.abstractitem.PlannerProductItem;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.items.IHeader;

public class PlannerAdapter extends FlexibleAdapter<IFlexible> {

    public PlannerAdapter(@Nullable List<IFlexible> items, @Nullable OnItemClickListener listener, boolean stableIds) {
        super(items, listener, stableIds);
    }

    public void removeItemsFromSection(IHeader header) {
        List items = getSectionItems(header);
        List<Integer> itemsToRemove = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            PlannerProductItem plannerProductItem = (PlannerProductItem) items.get(i);
            if (!plannerProductItem.getPlannerProductModel().isFeatured()) {
                itemsToRemove.add(getGlobalPositionOf(plannerProductItem));
            }
        }
        removeItems(itemsToRemove);
    }
}
