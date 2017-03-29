package com.rcl.excalibur.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.adapters.TriptychPagerAdapter;
import com.rcl.excalibur.custom.view.TriptychTabBarLayout;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.fragments.ItineraryFragment;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TriptychHomeView extends ActivityView<TriptychHomeActivity> {

    @Bind(R.id.pager_triptych_pager) ViewPager viewPager;
    @Bind(R.id.tab_triptych_tablayout) TriptychTabBarLayout tabBarLayout;

    public TriptychHomeView(TriptychHomeActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init() {
        if (getActivity() != null) {
            List<Fragment> triptychFragments = new ArrayList<>();
            triptychFragments.add(ItineraryFragment.newInstance());
            triptychFragments.add(DiscoverTabFragment.newInstance());

            viewPager.setAdapter(new TriptychPagerAdapter(getActivity().getSupportFragmentManager(), triptychFragments));
            tabBarLayout.setViewPager(viewPager);
        }
    }
}
