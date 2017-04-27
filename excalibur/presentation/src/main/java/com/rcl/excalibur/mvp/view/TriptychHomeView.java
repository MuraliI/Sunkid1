package com.rcl.excalibur.mvp.view;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.DayPickerActivity;
import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.adapters.TriptychPagerAdapter;
import com.rcl.excalibur.custom.view.ShipView;
import com.rcl.excalibur.custom.view.TriptychTabBarLayout;
import com.rcl.excalibur.fragments.BaseTripTychFragment;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.fragments.PlannerFragment;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.DayInformationUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TriptychHomeView extends ActivityView<TriptychHomeActivity, Void, Void> {

    @BindView(R.id.pager_triptych_pager) ViewPager viewPager;
    @BindView(R.id.tab_triptych_tablayout) TriptychTabBarLayout tabBarLayout;
    @BindView(R.id.ship_view) ShipView shipView;
    @BindView(R.id.text_ship_status) TextView shipLocationLabel;

    @BindView(R.id.date_picker_plans_tab) TextView datePickerDayLabel;

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

    public void onServiceCallCompleted(boolean successfully) {
        TriptychPagerAdapter adapter = (TriptychPagerAdapter) viewPager.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            Fragment adapterFragment = adapter.getFragmentForPosition(i);
            if (adapterFragment instanceof BaseTripTychFragment) {
                ((BaseTripTychFragment) adapterFragment).onServiceCallCompleted(successfully);
            }
        }
    }

    @OnClick(R.id.date_picker_plans_tab)
    public void showDayPicker() {
        ActivityUtils.startActivity(getActivity(), DayPickerActivity.getStartIntent(getActivity()));
    }

    public void addShipLocationValue(@NonNull List<EventModel> events, int day) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Resources resources = activity.getResources();
        if (events == null) {
            setTextShipLocation(resources.getString(R.string.empty_string),
                    resources.getString(R.string.day_number, day));
        } else {
            setTextShipLocation(DayInformationUtils.getShipLocation(events, day, resources),
                    resources.getString(R.string.day_number, day));
        }
    }

    private void setTextShipLocation(String textShip, String textDay) {
        shipLocationLabel.setText(textShip);
        datePickerDayLabel.setText(textDay);
    }
}
