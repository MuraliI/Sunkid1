package com.rcl.excalibur.adapters.planner;


import android.support.annotation.Nullable;

import com.rcl.excalibur.adapters.planner.abstractitem.PlannerHeader;
import com.rcl.excalibur.adapters.planner.abstractitem.PlannerProductItem;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IHeader;

public class PlannerAdapter<A> extends FlexibleAdapter {

    public PlannerAdapter(@Nullable List items, @Nullable Object listeners, boolean stableIds) {
        super(items, listeners, stableIds);
    }

    public void remove(IHeader iHeader) {
        PlannerHeader header = (PlannerHeader) iHeader;

        List items = getSectionItems(header);
        List<Integer> itemsToRemove = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            PlannerProductItem plannerProductItem = (PlannerProductItem) items.get(i);
            if (!plannerProductItem.getPlannerProductModel().isFeatured()) {
                itemsToRemove.add(i);
            }
        }

        removeItems(itemsToRemove);
    }
}
