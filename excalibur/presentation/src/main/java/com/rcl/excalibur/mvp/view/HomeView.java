package com.rcl.excalibur.mvp.view;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.HomeActivity;
import com.rcl.excalibur.adapters.PlanAdapter;
import com.rcl.excalibur.model.PlanModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeView extends ActivityView<HomeActivity> {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
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
        adapter = new PlanAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    public void addAll(List<PlanModel> list) {
        adapter.addAll(list);
    }
}
