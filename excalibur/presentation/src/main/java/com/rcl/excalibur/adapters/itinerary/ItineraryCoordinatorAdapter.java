package com.rcl.excalibur.adapters.itinerary;

import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.BaseCoordinatorAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.planner.PlannerHeaderDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.planner.PlannerProductDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.planner.SeparatorDelegateAdapter;

import java.util.List;

import io.reactivex.Observer;

import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_HEADER_VIEW;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_ITINERARY_PRODUCT_VIEW;
import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_SEPARATOR_VIEW;


public class ItineraryCoordinatorAdapter extends BaseCoordinatorAdapter {

    private static final int VIEW_TYPE_COUNT = 3;

    @SuppressWarnings("unchecked")
    public ItineraryCoordinatorAdapter(Observer observer) {
        super(observer);

        delegateAdapters = new SparseArrayCompat<>(VIEW_TYPE_COUNT);
        delegateAdapters.append(VIEW_TYPE_EXPANDABLE_HEADER_VIEW, new PlannerHeaderDelegateAdapter());
        delegateAdapters.append(VIEW_TYPE_ITINERARY_PRODUCT_VIEW, new PlannerProductDelegateAdapter());
        delegateAdapters.append(VIEW_TYPE_SEPARATOR_VIEW, new SeparatorDelegateAdapter());
    }

    public int getItemPosition(RecyclerViewType elem) {
        return items.indexOf(elem);
    }

    @SuppressWarnings("unchecked")
    public void add(List<Integer> positions, List<RecyclerViewType> itemsToAdd) {
        for (int i = 0; i < itemsToAdd.size(); i++) {
            int positionToInsert = positions.get(i);

            items.add(positionToInsert, itemsToAdd.get(i));
            notifyItemInserted(positionToInsert);
        }
    }

    public void remove(List<Integer> positions, List<RecyclerViewType> itemsToRemove) {
        for (int i = itemsToRemove.size() - 1; i >= 0; i--) {
            items.remove(itemsToRemove.get(i));
            notifyItemRemoved(positions.get(i));
        }
    }
}
