package com.rcl.excalibur.mvp.view;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.adapters.PlanListAdapter;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.rcl.excalibur.adapters.PlanListAdapter.POSITION_DINING;
import static com.rcl.excalibur.adapters.PlanListAdapter.POSITION_ENTERTAINMENT;
import static com.rcl.excalibur.adapters.PlanListAdapter.POSITION_ROYAL_ACTIVITY;
import static com.rcl.excalibur.adapters.PlanListAdapter.POSITION_SHOPPING;
import static com.rcl.excalibur.adapters.PlanListAdapter.POSITION_SHOREX;
import static com.rcl.excalibur.adapters.PlanListAdapter.POSITION_SPA;


public class PlanListView extends ActivityView<PlanListActivity> {
    @Bind(R.id.plan_list_tab_layout) TabLayout tabLayout;
    @Bind(R.id.plan_list_view_pager) ViewPager viewPager;

    public PlanListView(PlanListActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init() {
        viewPager.setAdapter(new PlanListAdapter(getFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(POSITION_ROYAL_ACTIVITY).setText(R.string.royal_activity);
        tabLayout.getTabAt(POSITION_DINING).setText(R.string.dining);
        tabLayout.getTabAt(POSITION_SHOPPING).setText(R.string.shopping);
        tabLayout.getTabAt(POSITION_SPA).setText(R.string.spa);
        tabLayout.getTabAt(POSITION_SHOREX).setText(R.string.shorex);
        tabLayout.getTabAt(POSITION_ENTERTAINMENT).setText(R.string.entertainment);
    }
}
