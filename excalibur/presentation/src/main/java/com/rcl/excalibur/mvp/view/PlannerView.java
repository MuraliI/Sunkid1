package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.adapters.planner.abstractitem.PlannerProductItem;
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
    @Bind(R.id.layout_planner_all_day) LinearLayout allDayLayout;

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

        PlannerFragment fragment = getFragment();
        if (fragment == null) {
            return;
        }
        adapter = new FlexibleAdapter<>(null, fragment, true);
        adapter.setDisplayHeadersAtStartUp(true).setStickyHeaders(true);
        recyclerView.setLayoutManager(new RoyalLinearLayoutManager(fragment.getContext()));
        recyclerView.setAdapter(adapter);
    }

    public void addPlannerItems(List<AbstractFlexibleItem> items) {
        adapter.addItems(0, items);
    }

    public void onItemClick(int position) {
        AbstractFlexibleItem item = adapter.getItem(position);
        if (item instanceof PlannerProductItem) {
            PlannerProductItem productItem = (PlannerProductItem) item;
            BaseActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            activity.startActivity(ProductDetailActivity.getIntent(activity,
                    productItem.getPlannerProductModel().getProductId()));
        }
    }

    public void showAllDayLayout() {
        allDayLayout.setVisibility(View.VISIBLE);
    }
}
