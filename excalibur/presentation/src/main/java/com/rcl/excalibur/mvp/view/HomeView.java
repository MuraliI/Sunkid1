package com.rcl.excalibur.mvp.view;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.HomeActivity;
import com.rcl.excalibur.adapters.PlanAdapter;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeView extends ActivityView<HomeActivity, Void, DiscoverItemModel> {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private PlanAdapter adapter;

    public HomeView(HomeActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init() {
        final HomeActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        adapter = new PlanAdapter(adapterObserver);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    public void addAll(List<DiscoverItemModel> list) {
        adapter.addAll(list);
    }
}
