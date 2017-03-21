package com.rcl.excalibur.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.adapters.TriptychPagerAdapter;
import com.rcl.excalibur.custom.view.TriptychTabBarLayout;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.fragments.MeTabFragment;
import com.rcl.excalibur.fragments.PlansTabFragment;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TriptyHomechView extends ActivityView<TriptychHomeActivity> {

    @Bind(R.id.pager_triptych_pager) ViewPager viewPager;
    @Bind(R.id.tab_triptych_tablayout) TriptychTabBarLayout tabBarLayout;

    public TriptyHomechView(TriptychHomeActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init() {
        if (getActivity() != null) {
            List<Fragment> triptychFragments = new ArrayList<>();
            triptychFragments.add(PlansTabFragment.newInstance());
            triptychFragments.add(DiscoverTabFragment.newInstance());
            triptychFragments.add(MeTabFragment.newInstance());

            viewPager.setAdapter(new TriptychPagerAdapter(getActivity().getSupportFragmentManager(), triptychFragments));
            tabBarLayout.setViewPager(viewPager);
        }
    }
}
