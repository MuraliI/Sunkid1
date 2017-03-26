package com.rcl.excalibur.mvp.presenter;

import android.content.Intent;

import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.mvp.view.DiscoverTabView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

public class DiscoverTabPresenter implements FragmentPresenter {

    private DiscoverTabView view;

    public DiscoverTabPresenter(DiscoverTabView view) {
        this.view = view;
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_DISCOVER);
    }

    public void openListScreen(int fragmentToShow) {
        Intent intent = new Intent(view.getActivity(), PlanListActivity.class);
        intent.putExtra(PlanListActivity.EXTRA_FRAGMENT_TYPE, fragmentToShow);
        ActivityUtils.startActivity(view.getActivity(), intent);
    }
}

