package com.rcl.excalibur.custom.itinerary;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


public class RoyalLinearLayoutManager extends LinearLayoutManager {
    private OnFirstTimeListener listener;

    public interface OnFirstTimeListener {
        void isShowingItems(int visibleItemCount);
    }

    public RoyalLinearLayoutManager(Context context, OnFirstTimeListener listener) {
        super(context);
        this.listener = listener;
    }

    public RoyalLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public RoyalLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        RoyalLinearSmoothScroller smoothScroller = new RoyalLinearSmoothScroller(recyclerView.getContext());
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);

        final int firstVisibleItemPosition = findFirstVisibleItemPosition();
        //Timber.w("firstVisibleItemPosition %s", firstVisibleItemPosition);
        if (firstVisibleItemPosition != 0) {
            // this avoids trying to handle un-needed calls
            if (firstVisibleItemPosition == -1) {
                //not initialized, or no items shown, so hide fast-scroller
                //Timber.w("not initialized, or no items shown, so hide fast-scroller");
            }
            return;
        }
        final int lastVisibleItemPosition = findLastVisibleItemPosition();
        int itemsShown = lastVisibleItemPosition - firstVisibleItemPosition + 1;
        //Timber.w("itemsShown %s", itemsShown);
        if (itemsShown > 0) {
            listener.isShowingItems(itemsShown);
        }
    }
}
