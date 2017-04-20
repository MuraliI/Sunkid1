package com.rcl.excalibur.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

@Deprecated
public class PlanListAdapter extends FragmentStatePagerAdapter {
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
        return null;
    }
}
