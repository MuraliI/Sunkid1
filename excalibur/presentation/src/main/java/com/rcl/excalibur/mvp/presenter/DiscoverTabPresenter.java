package com.rcl.excalibur.mvp.presenter;

import android.content.Intent;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.mvp.view.DiscoverTabView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

public class DiscoverTabPresenter implements BasePresenter {

    private DiscoverTabView view;

    public DiscoverTabPresenter(DiscoverTabView view) {
        this.view = view;
        initInjection();
        init();

        AnalyticsUtils.trackState(AnalyticsConstants.KEY_DISCOVER);
    }

    private void init() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
    }

    private void initInjection() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        /*activity.getApplicationComponent().inject(this);*/
    }

    public void openListScreen(int fragmentToShow) {
        Intent intent = new Intent(view.getActivity(), PlanListActivity.class);
        intent.putExtra(PlanListActivity.EXTRA_FRAGMENT_TYPE, fragmentToShow);
        ActivityUtils.startActivity(view.getActivity(), intent);
    }

}

