package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.itinerary.ItineraryCoordinatorAdapter;
import com.rcl.excalibur.custom.itinerary.RoyalLinearLayoutManager;
import com.rcl.excalibur.fragments.PlannerFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlannerView extends FragmentView<PlannerFragment, Void, Void> {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private ItineraryCoordinatorAdapter adapter;

    public PlannerView(PlannerFragment fragment) {
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

    public void scrollToPosition(RecyclerViewType elem) {
        int pos = adapter.getItemPosition(elem);
        if (pos < adapter.getItemCount()) {
            recyclerView.smoothScrollToPosition(pos);
        }
    }

    @SuppressWarnings("unchecked")
    public void addPlans(List<RecyclerViewType> productModelList) {
        adapter.addAll(productModelList);
    }
}
