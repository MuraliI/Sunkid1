package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDeckMapActivity;
import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.data.mapper.SubCategoryResponseDataMapper;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.data.repository.SubCategoriesDataRepository;
import com.rcl.excalibur.data.service.DiscoverServicesImpl;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.interactor.GetSubCategoriesUseCase;
import com.rcl.excalibur.mvp.view.DiscoverTabView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;


public class DiscoverTabPresenter {
    private static final int LIMIT_CLICKS = 5;
    private DiscoverTabView view;
    private GetProductsUseCase getProductsUseCase;
    private GetSubCategoriesUseCase getSubCategoriesUseCase;
    protected int countBoatOnClick;

    public DiscoverTabPresenter(DiscoverTabView view, GetProductsUseCase getProductsUseCase) {
        this.view = view;
        this.getProductsUseCase = getProductsUseCase;
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_DISCOVER);
    }

    public void init() {
        getProductsUseCase.execute(null);
        DiscoverServicesImpl impl = new DiscoverServicesImpl(new ProductDataRepository());
        impl.setSubCategoryRepository(new SubCategoriesDataRepository());
        impl.setSubCategoryResponseDataMapper(new SubCategoryResponseDataMapper());
        getSubCategoriesUseCase = new GetSubCategoriesUseCase(impl);
        getSubCategoriesUseCase.execute(null);
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

    public void shipOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.startActivity(activity, ProductDeckMapActivity.getIntent(activity, null));
    }
}

