package com.rcl.excalibur.mvp.view;


import android.support.v4.app.Fragment;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.utils.Preconditions;

import butterknife.ButterKnife;

import static com.rcl.excalibur.fragments.DiscoverItemListFragment.DINING;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.ENTERTAINMENT;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.ROYAL_ACTIVITY;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.SHOPPING;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.SHOREX;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.SPA;
import static com.rcl.excalibur.fragments.DiscoverItemListFragment.newInstance;


public class PlanListView extends ActivityView<PlanListActivity> {

    private int fragmentToShow = 0;
    public static final int POSITION_ROYAL_ACTIVITY = 0;
    public static final int POSITION_DINING = 1;
    public static final int POSITION_SHOPPING = 2;
    public static final int POSITION_SPA = 3;
    public static final int POSITION_SHOREX = 4;
    public static final int POSITION_ENTERTAINMENT = 5;

    public PlanListView(PlanListActivity activity, int fragmentToShow) {
        super(activity);
        ButterKnife.bind(this, activity);
        this.fragmentToShow = fragmentToShow;
    }

    public void init() {

        switch (fragmentToShow) {
            case POSITION_ROYAL_ACTIVITY:
                callFragment(newInstance(ROYAL_ACTIVITY));
                break;
            case POSITION_DINING:
                callFragment(newInstance(DINING));
                break;
            case POSITION_SHOPPING:
                callFragment(newInstance(SHOPPING));
                break;
            case POSITION_SPA:
                callFragment(newInstance(SPA));
                break;
            case POSITION_SHOREX:
                callFragment(newInstance(SHOREX));
                break;
            case POSITION_ENTERTAINMENT:
                callFragment(newInstance(ENTERTAINMENT));
                break;
            default:
                Preconditions.unrecheable();
        }
    }

    private void callFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.full_content,
                fragment).commit();
    }
}
