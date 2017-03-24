package com.rcl.excalibur.mvp.view.itinerary;


import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.itinerary.ItineraryCoordinatorAdapter;
import com.rcl.excalibur.custom.itinerary.RoyalLinearLayoutManager;
import com.rcl.excalibur.fragments.ItineraryFragment;
import com.rcl.excalibur.mvp.view.FragmentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItineraryView extends FragmentView<ItineraryFragment> {

    public interface OnRefreshDataListener {
        void onRefresh();
    }

    private static final int SCROLL_OFFSET = 0;
    private static final int SCROLL_DELAY = 500;

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.layout_swipe_refresh) SwipeRefreshLayout refreshLayout;

    private ItineraryCoordinatorAdapter adapter;

    public ItineraryView(ItineraryFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        adapter = new ItineraryCoordinatorAdapter(null);
        recyclerView.setLayoutManager(new RoyalLinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    public void setOnRefreshDataListener(OnRefreshDataListener onRefreshDataListener) {
        if (onRefreshDataListener != null) {
            refreshLayout.setOnRefreshListener(onRefreshDataListener::onRefresh);
        }
    }

    public void setIsLoadingData(boolean isLoadingData) {
        refreshLayout.setRefreshing(isLoadingData);
    }

    public void scrollToPosition(int pos) {
        if (pos < adapter.getItemCount()) {
            recyclerView.smoothScrollToPosition(pos);
        }
    }

    public void setGreetingText(RecyclerViewType greetingText) {
        adapter.addViewTypeOnceAndNotify(greetingText);
    }

    public void addPlans(List<RecyclerViewType> productModelList) {
        adapter.clearAndAddAll(productModelList);
    }

}
