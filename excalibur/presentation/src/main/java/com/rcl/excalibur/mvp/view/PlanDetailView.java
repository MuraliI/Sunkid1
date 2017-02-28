package com.rcl.excalibur.mvp.view;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.DetailViewCoordinatorAdapter;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PlanDetailView extends ActivityView {

    public static final String EMPTY_STRING = "";

    @Bind(R.id.rv_plan_details) RecyclerView planDetailRecycler;
    @Bind(R.id.ct_detail) CollapsingToolbarLayout detailCoordinator;
    @Bind(R.id.at_detail) Toolbar detailToolbar;
    @Bind(R.id.tv_plan_name) TextView planName;

    private DetailViewCoordinatorAdapter adapter;

    public PlanDetailView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        if (activity != null) {
            activity.setSupportActionBar(detailToolbar);
            activity.setTitle(EMPTY_STRING);
        }
    }

    public void setDetailTitle(String title) {
        planName.setText(title);
    }

    public void render(SparseArrayCompat<DelegateAdapter> adapterList, List<RecyclerViewType> viewTypes) {
        adapter = new DetailViewCoordinatorAdapter(viewObserver, adapterList, viewTypes);
        planDetailRecycler.setAdapter(adapter);
        planDetailRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
