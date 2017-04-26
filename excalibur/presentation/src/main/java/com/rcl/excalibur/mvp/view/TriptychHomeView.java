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
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.fragments.BaseTripTychFragment;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.fragments.PlannerFragment;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.PortModel;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.DayInformationUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TriptychHomeView extends ActivityView<TriptychHomeActivity, Void, Void> {

    private static final String PORT_TYPE_EMBARK = "EMBARK";
    private static final String PORT_TYPE_DOCKED = "DOCKED";
    private static final String PORT_TYPE_DEBARK = "DEBARK";
    private static final String PORT_TYPE_CRUISING = "CRUISING";

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
            setTextShipLocation(resources.getString(R.string.empty_string), resources.getString(R.string.day_number, day));
        } else {
            setTextShipLocation(getShipLocation(events, day, resources), resources.getString(R.string.day_number, day));
        }
    }

    public static String getShipLocation(List<EventModel> events, int day, Resources resources) {
        String shipLocation;
        PortModel sailPort = DayInformationUtils.getSailPortByDay(events, day);

        String modelPortType = sailPort.getPortType();

        if (PORT_TYPE_EMBARK.equals(modelPortType) || PORT_TYPE_DOCKED.equals(modelPortType) || PORT_TYPE_DEBARK.equals(modelPortType)) {
            shipLocation = sailPort.getPortName();
        } else if (PORT_TYPE_CRUISING.equals(modelPortType)) {
            shipLocation = resources.getString(R.string.port_type_at_sea);
        } else {
            shipLocation = ConstantsUtil.EMPTY;
        }
        return shipLocation;
    }

    private void setTextShipLocation(String textShip, String textDay) {
        shipLocationLabel.setText(textShip);
        datePickerDayLabel.setText(textDay);
    }
}
