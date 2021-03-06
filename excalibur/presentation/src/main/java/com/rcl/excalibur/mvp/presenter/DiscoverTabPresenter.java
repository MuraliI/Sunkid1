package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DayPickerActivity;
import com.rcl.excalibur.activity.DeckMapActivity;
import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.mvp.view.DiscoverTabView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;


public class DiscoverTabPresenter {
    private static final int LIMIT_CLICKS = 5;
    private DiscoverTabView view;

    protected int countBoatOnClick;

    public DiscoverTabPresenter(DiscoverTabView view, boolean serviceAlreadyFinished) {
        this.view = view;
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_DISCOVER);
        init(serviceAlreadyFinished);
    }

    protected void init(boolean serviceAlreadyFinished) {
        view.init();
        view.showLoadingView(true);
        if (serviceAlreadyFinished) {
            serviceCallCompleted();
        }
    }

    public void openListScreen(int fragmentToShow) {
        view.openListScreen(fragmentToShow);
    }

    public void boatOnClick() {
        countBoatOnClick++;
        if (countBoatOnClick >= LIMIT_CLICKS) {
            final BaseActivity activity = view.getActivity();
            if (activity == null) {
                return;
            }
            ActivityUtils.startActivity(activity, NameActivity.getStartIntent(activity));
        }
    }

    public void serviceCallCompleted() {
        view.serviceCallCompleted();
        view.showLoadingView(false);
    }

    public void openDayPicker() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.startActivity(activity, DayPickerActivity.getStartIntent(activity));
    }

    public void shipOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.startActivity(activity, DeckMapActivity.getIntent(activity, null));
    }
}

