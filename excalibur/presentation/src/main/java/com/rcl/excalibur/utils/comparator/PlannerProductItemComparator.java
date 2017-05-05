package com.rcl.excalibur.utils.comparator;


import com.rcl.excalibur.adapters.planner.abstractitem.PlannerProductItem;

import java.util.Comparator;

public class PlannerProductItemComparator implements Comparator<PlannerProductItem> {
    @Override
    public int compare(PlannerProductItem o1, PlannerProductItem o2) {
        return o1.getPlannerProductModel().compareTo(o2.getPlannerProductModel());
    }
}
