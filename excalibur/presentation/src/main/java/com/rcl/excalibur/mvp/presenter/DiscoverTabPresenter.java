package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DayPickerActivity;
import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateUseCase;
import com.rcl.excalibur.mvp.view.DiscoverTabView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;


public class DiscoverTabPresenter {
    private static final int LIMIT_CLICKS = 5;
    private DiscoverTabView view;
    private GetProductsUseCase getProductsUseCase;
    private GetSaildDateUseCase getSaildDateUseCase;

    protected int countBoatOnClick;

    public DiscoverTabPresenter(DiscoverTabView view,
                                GetProductsUseCase getProductsUseCase,
                                GetSaildDateUseCase getSaildDateUseCase) {
        this.view = view;
        this.getProductsUseCase = getProductsUseCase;
        this.getSaildDateUseCase = getSaildDateUseCase;
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_DISCOVER);
    }

    public void init() {
        getProductsUseCase.execute(null);
        getSaildDateUseCase.execute(null);
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
    public void openDayPicker() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.startActivity(activity, DayPickerActivity.getStartIntent(activity));

    }
}

