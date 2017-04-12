package com.rcl.excalibur.mvp.view;


import android.support.v4.app.Fragment;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.base.ActivityView;
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


public class PlanListView extends ActivityView<PlanListActivity, Void, DiscoverItemModel> {
    public static final int POSITION_ROYAL_ACTIVITY = 0;
    public static final int POSITION_DINING = 1;
    public static final int POSITION_SHOPPING = 2;
    public static final int POSITION_SPA = 3;
    public static final int POSITION_SHOREX = 4;
    public static final int POSITION_ENTERTAINMENT = 5;

    public PlanListView(PlanListActivity activity) {
        super(activity);
    }

    public void init(int fragmentToShow) {
        final PlanListActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        Fragment fragment = createFragment(activity, fragmentToShow);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.full_content,
                fragment).commit();
    }

    private Fragment createFragment(PlanListActivity activity, int fragmentToShow) {
        Fragment fragment = null;
        //Objects needed for Analytics tracking
        AnalyticEvent analyticEvent = new AnalyticEvent(AnalyticsConstants.KEY_FILTER_DISCOVER);
        String categorySelected = "";
        switch (fragmentToShow) {
            case POSITION_ROYAL_ACTIVITY:
                fragment = newInstance(ROYAL_ACTIVITY);
                categorySelected = activity.getString(R.string.category_royal_activity);
                break;
            case POSITION_DINING:
                fragment = newInstance(DINING);
                categorySelected = activity.getString(R.string.category_dining);
                break;
            case POSITION_SHOPPING:
                fragment = newInstance(SHOPPING);
                categorySelected = activity.getString(R.string.category_shopping);
                break;
            case POSITION_SPA:
                fragment = newInstance(SPA);
                categorySelected = activity.getString(R.string.category_spa);
                break;
            case POSITION_SHOREX:
                fragment = newInstance(SHOREX);
                categorySelected = activity.getString(R.string.category_shorex);
                break;
            case POSITION_ENTERTAINMENT:
                fragment = newInstance(ENTERTAINMENT);
                categorySelected = activity.getString(R.string.category_entertainment);
                break;
            default:
                Preconditions.unreachable();
        }
        AnalyticsUtils.trackEvent(analyticEvent.addKeyValue(AnalyticsConstants.KEY_FILTER_CATEGORY, categorySelected));
        return fragment;
    }

}
