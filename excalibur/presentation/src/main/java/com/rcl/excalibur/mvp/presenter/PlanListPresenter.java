package com.rcl.excalibur.mvp.presenter;


import android.support.v4.app.Fragment;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.PlanListView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.Preconditions;

import static com.rcl.excalibur.fragments.DiscoverItemListFragment.DINING;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.ENTERTAINMENT;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.ROYAL_ACTIVITY;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.SHOPPING;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.SHOREX;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.SPA;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.newInstance;

public class PlanListPresenter implements BasePresenter {
    public static final int POSITION_ROYAL_ACTIVITY = 0;
    public static final int POSITION_DINING = 1;
    public static final int POSITION_SHOPPING = 2;
    public static final int POSITION_SPA = 3;
    public static final int POSITION_SHOREX = 4;
    public static final int POSITION_ENTERTAINMENT = 5;
    private PlanListView view;
    private int fragmentToShow = 0;

    public PlanListPresenter(PlanListView view, int fragmentToShow) {
        this.view = view;
        this.fragmentToShow = fragmentToShow;
        init();
    }

    private void init() {
        Fragment fragment = newInstance(ROYAL_ACTIVITY);
        switch (fragmentToShow) {
            case POSITION_ROYAL_ACTIVITY:
                fragment = newInstance(ROYAL_ACTIVITY);
                break;
            case POSITION_DINING:
                fragment = newInstance(DINING);
                break;
            case POSITION_SHOPPING:
                fragment = newInstance(SHOPPING);
                break;
            case POSITION_SPA:
                fragment = newInstance(SPA);
                break;
            case POSITION_SHOREX:
                fragment = newInstance(SHOREX);
                break;
            case POSITION_ENTERTAINMENT:
                fragment = newInstance(ENTERTAINMENT);
                break;
            default:
                Preconditions.unrecheable();
        }

        view.setAdapterObserver(new AdapterObserver(this));
        view.init(fragment);
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    public class AdapterObserver extends DefaultPresentObserver<DiscoverItemModel, PlanListPresenter> {

        public AdapterObserver(PlanListPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {

//   TODO         Invoke Details screen
        }
    }

}
