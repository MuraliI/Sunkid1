package com.rcl.excalibur.mvp.presenter;


import android.support.v4.app.Fragment;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.PlanListView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.Preconditions;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import static com.rcl.excalibur.fragments.ProductsListFragment.DINING;
import static com.rcl.excalibur.fragments.ProductsListFragment.ENTERTAINMENT;
import static com.rcl.excalibur.fragments.ProductsListFragment.ROYAL_ACTIVITY;
import static com.rcl.excalibur.fragments.ProductsListFragment.SHOPPING;
import static com.rcl.excalibur.fragments.ProductsListFragment.SHOREX;
import static com.rcl.excalibur.fragments.ProductsListFragment.SPA;
import static com.rcl.excalibur.fragments.ProductsListFragment.newInstance;

public class PlanListPresenter implements ActivityPresenter {
    public static final int POSITION_ROYAL_ACTIVITY = 0;
    public static final int POSITION_DINING = 1;
    public static final int POSITION_SHOPPING = 2;
    public static final int POSITION_SPA = 3;
    public static final int POSITION_SHOREX = 4;
    public static final int POSITION_ENTERTAINMENT = 5;
    private final PlanListView view;
    private final int fragmentToShow;

    public PlanListPresenter(PlanListView view, int fragmentToShow) {
        this.view = view;
        this.fragmentToShow = fragmentToShow;
    }

    @Override
    public PlanListView getView() {
        return view;
    }

    public void init() {
        Fragment fragment = newInstance(ROYAL_ACTIVITY);

        //Objects needed for Analytics tracking
        AnalyticEvent analyticEvent = new AnalyticEvent(AnalyticsConstants.KEY_FILTER_DISCOVER);
        String categorySelected = "";

        switch (fragmentToShow) {
            case POSITION_ROYAL_ACTIVITY:
                fragment = newInstance(ROYAL_ACTIVITY);
                categorySelected = view.getActivity().getString(R.string.category_royal_activity);
                break;
            case POSITION_DINING:
                fragment = newInstance(DINING);
                categorySelected = view.getActivity().getString(R.string.category_dining);
                break;
            case POSITION_SHOPPING:
                fragment = newInstance(SHOPPING);
                categorySelected = view.getActivity().getString(R.string.category_shopping);
                break;
            case POSITION_SPA:
                fragment = newInstance(SPA);
                categorySelected = view.getActivity().getString(R.string.category_spa);
                break;
            case POSITION_SHOREX:
                fragment = newInstance(SHOREX);
                categorySelected = view.getActivity().getString(R.string.category_shorex);
                break;
            case POSITION_ENTERTAINMENT:
                fragment = newInstance(ENTERTAINMENT);
                categorySelected = view.getActivity().getString(R.string.category_entertainment);
                break;
            default:
                Preconditions.unrecheable();
        }

        view.setAdapterObserver(new AdapterObserver(this));
        view.init(fragment);
        AnalyticsUtils.trackEvent(analyticEvent.addKeyValue(AnalyticsConstants.KEY_FILTER_CATEGORY, categorySelected));

    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    public class AdapterObserver extends DefaultPresentObserver<DiscoverItemModel, PlanListPresenter> {

        AdapterObserver(PlanListPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {
            //TODO Invoke Details screen
        }
    }

}
