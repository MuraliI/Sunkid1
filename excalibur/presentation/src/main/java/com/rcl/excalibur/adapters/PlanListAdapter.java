package com.rcl.excalibur.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rcl.excalibur.data.utils.Preconditions;

import static com.rcl.excalibur.fragments.ProductsListFragment.DINING;
import static com.rcl.excalibur.fragments.ProductsListFragment.ENTERTAINMENT;
import static com.rcl.excalibur.fragments.ProductsListFragment.ROYAL_ACTIVITY;
import static com.rcl.excalibur.fragments.ProductsListFragment.SHOPPING;
import static com.rcl.excalibur.fragments.ProductsListFragment.SHOREX;
import static com.rcl.excalibur.fragments.ProductsListFragment.SPA;
import static com.rcl.excalibur.fragments.ProductsListFragment.newInstance;


public class PlanListAdapter extends FragmentStatePagerAdapter {
    private static final int POSITION_ROYAL_ACTIVITY = 0;
    private static final int POSITION_DINING = 1;
    private static final int POSITION_SHOPPING = 2;
    private static final int POSITION_SPA = 3;
    private static final int POSITION_SHOREX = 4;
    private static final int POSITION_ENTERTAINMENT = 5;
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
                Preconditions.unreachable();
                return null;
        }
    }
}
