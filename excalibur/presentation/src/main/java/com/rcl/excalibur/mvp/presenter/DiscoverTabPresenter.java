package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.mvp.view.DiscoverTabView;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

public class DiscoverTabPresenter implements FragmentPresenter {
    private DiscoverTabView view;
    private GetProductsUseCase getProductsUseCase;

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
}

