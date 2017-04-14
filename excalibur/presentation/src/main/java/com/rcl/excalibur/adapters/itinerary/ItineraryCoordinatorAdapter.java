package com.rcl.excalibur.adapters.itinerary;

import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.BaseCoordinatorAdapter;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.itinerary.ExpandableHeaderDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.itinerary.GreetingsDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.itinerary.ItineraryProductDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.itinerary.SeparatorCalendarDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.itinerary.SeparatorDelegateAdapter;

import java.util.List;

import io.reactivex.Observer;


public class ItineraryCoordinatorAdapter extends BaseCoordinatorAdapter {

    private static final int VIEW_TYPE_COUNT = 3;

    public ItineraryCoordinatorAdapter(Observer observer) {
        super(observer);
        delegateAdapters = new SparseArrayCompat<DelegateAdapter>(VIEW_TYPE_COUNT);
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_HEADER_VIEW, new ExpandableHeaderDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_GREETINGS, new GreetingsDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_ITINERARY_PRODUCT_VIEW, new ItineraryProductDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_CALENDAR_VIEW, new SeparatorCalendarDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_SEPARATOR_VIEW, new SeparatorDelegateAdapter());
    }

    public void clearAndAddAll(List<RecyclerViewType> items) {
        RecyclerViewType greetings = null;
        for (Object item : this.items) {
            if (RecyclerViewConstants.VIEW_TYPE_GREETINGS == ((RecyclerViewType) item).getViewType()) {
                greetings = (RecyclerViewType) item;
                break;
            }
        }

        clearItemsAndNotify();
        if (greetings != null) {
            addViewTypeOnceAndNotify(greetings);
        }
        addAll(items);
    }

    public int getItemPosition(RecyclerViewType elem) {
        return items.indexOf(elem);
    }

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
