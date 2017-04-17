package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.custom.itinerary.RoyalLinearLayoutManager;
import com.rcl.excalibur.fragments.PlannerFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public class PlannerView extends FragmentView<PlannerFragment, Void, Void> {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private FlexibleAdapter<AbstractFlexibleItem> adapter;

    public PlannerView(PlannerFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        adapter = new FlexibleAdapter<>(null, null, true);
        adapter.setDisplayHeadersAtStartUp(true).setStickyHeaders(true);
        recyclerView.setLayoutManager(new RoyalLinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    public void addPlannerItems(List<AbstractFlexibleItem> items) {
        adapter.addItems(0, items);
    }
}
