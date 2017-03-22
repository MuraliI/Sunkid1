package com.rcl.excalibur.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class TriptychPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> triptychFragments;

    public TriptychPagerAdapter(FragmentManager fm, @NonNull List<Fragment> triptychFragments) {
        super(fm);
        this.triptychFragments = new ArrayList<>(triptychFragments);
    }

    @Override
    public Fragment getItem(int position) {
        return triptychFragments.get(position);
    }

    @Override
    public int getCount() {
        return triptychFragments.size();
    }
}
