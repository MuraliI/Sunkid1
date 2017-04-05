package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.mvp.view.DiscoverTabView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;


public class DiscoverTabPresenter implements FragmentPresenter {
    private static final int LIMIT_CLICKS = 5;
    private DiscoverTabView view;
    private GetProductsUseCase getProductsUseCase;
    private int countBoatOnClick;

    public DiscoverTabPresenter(DiscoverTabView view, GetProductsUseCase getProductsUseCase) {
        this.view = view;
        this.getProductsUseCase = getProductsUseCase;
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_DISCOVER);
    }

    public void init() {
        getProductsUseCase.execute(null);
    }

    public void openListScreen(int fragmentToShow) {

        view.openListScreen(fragmentToShow);

    }

    @Override
    public DiscoverTabView getView() {
        return view;
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
}

