package com.rcl.excalibur.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rcl.excalibur.utils.Preconditions;

import static com.rcl.excalibur.fragments.DiscoverFragment.DINING;
import static com.rcl.excalibur.fragments.DiscoverFragment.ENTERTAINMENT;
import static com.rcl.excalibur.fragments.DiscoverFragment.ROYAL_ACTIVITY;
import static com.rcl.excalibur.fragments.DiscoverFragment.SHOPPING;
import static com.rcl.excalibur.fragments.DiscoverFragment.SHOREX;
import static com.rcl.excalibur.fragments.DiscoverFragment.SPA;
import static com.rcl.excalibur.fragments.DiscoverFragment.newInstance;


public class PlanListAdapter extends FragmentStatePagerAdapter {
    public static final int POSITION_ROYAL_ACTIVITY = 0;
    public static final int POSITION_DINING = 1;
    public static final int POSITION_SHOPPING = 2;
    public static final int POSITION_SPA = 3;
    public static final int POSITION_SHOREX = 4;
    public static final int POSITION_ENTERTAINMENT = 5;
    private static final int NUM_TABS = 6;

    public PlanListAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POSITION_ROYAL_ACTIVITY:
                return newInstance(ROYAL_ACTIVITY);
            case POSITION_DINING:
                return newInstance(DINING);
            case POSITION_SHOPPING:
                return newInstance(SHOPPING);
            case POSITION_SPA:
                return newInstance(SPA);
            case POSITION_SHOREX:
                return newInstance(SHOREX);
            case POSITION_ENTERTAINMENT:
                return newInstance(ENTERTAINMENT);
            default:
                Preconditions.unrecheable();
                return null;
        }
    }
}
