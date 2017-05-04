package com.rcl.excalibur.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.DiningMenuActivity;
import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.adapters.TriptychPagerAdapter;
import com.rcl.excalibur.custom.view.ShipView;
import com.rcl.excalibur.custom.view.TriptychTabBarLayout;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.fragments.PlannerFragment;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TriptychHomeView extends ActivityView<TriptychHomeActivity, Void, Void> {

    @BindView(R.id.pager_triptych_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_triptych_tablayout)
    TriptychTabBarLayout tabBarLayout;
    @BindView(R.id.ship_view)
    ShipView shipView;
    @BindView(R.id.text_ship_status)
    TextView shipLocationLabel;

    @BindView(R.id.date_picker_plans_tab)
    TextView datePickerDayLabel;

    public TriptychHomeView(TriptychHomeActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init() {
        final TriptychHomeActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        List<Fragment> triptychFragments = new ArrayList<>();
        PlannerFragment plannerFragment = PlannerFragment.newInstance();
        shipView.subscribeToSizeUpdate(plannerFragment);

        triptychFragments.add(plannerFragment);
        triptychFragments.add(DiscoverTabFragment.newInstance());

        viewPager.setAdapter(new TriptychPagerAdapter(activity.getSupportFragmentManager(), triptychFragments));
        viewPager.setPageTransformer(false, (view, position) -> view.setAlpha(1.0f - Math.abs(position)));
        tabBarLayout.attachToViewPager(viewPager);
        tabBarLayout.subscribeToScrollUpdates(integerFloatPair -> shipView.syncTabsScroll(integerFloatPair));
    }

    @OnClick(R.id.date_picker_plans_tab)
    public void showDayPicker() {
        ActivityUtils.startActivity(getActivity(), DiningMenuActivity.getStartIntent(getActivity(), "GIOV"));
    }

    public void setTextShipLocation(String textShip, String textDay) {
        shipLocationLabel.setText(textShip);
        datePickerDayLabel.setText(textDay);
    }

    public PagerAdapter getViewAdapter() {
        return viewPager.getAdapter();
    }
}
