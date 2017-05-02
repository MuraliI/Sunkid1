package com.rcl.excalibur.mvp.presenter;

import android.support.v4.view.ViewPager;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.DiningMenuPagerAdapter;
import com.rcl.excalibur.data.repository.MenuDataRepository;
import com.rcl.excalibur.domain.interactor.GetMenuDbUseCase;
import com.rcl.excalibur.fragments.MenuListFragment;
import com.rcl.excalibur.mvp.view.DiningMenuView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import java.util.List;

import timber.log.Timber;

public class DiningMenuPresenter {

    private final DiningMenuView view;

    public DiningMenuPresenter(DiningMenuView view) {
        this.view = view;
    }

    public void init(String venueCode) {
        view.init(venueCode);
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    public void createFragmentMenu(String idTypeMenu, ViewPager viewPager) {
        AnalyticEvent analyticEvent = new AnalyticEvent(AnalyticsConstants.KEY_FILTER_DISCOVER);
        GetMenuDbUseCase getMenuDbUseCase = new GetMenuDbUseCase(new MenuDataRepository());
        List<String> menusName = getMenuDbUseCase.getAllMenuName();
        if (menusName != null && !menusName.isEmpty()) {
            addFragmentsToPager(menusName, viewPager);
            AnalyticsUtils.trackEvent(analyticEvent.addKeyValue(AnalyticsConstants.KEY_FILTER_CATEGORY, idTypeMenu));
        } else {
            Timber.e("Don't find information for menu");
        }
    }

    private void addFragmentsToPager(List<String> menusName, ViewPager viewPager) {
        DiningMenuPagerAdapter adapter = new DiningMenuPagerAdapter(view.getFragmentManager());
        for (String menuName : menusName) {
            adapter.addFragment(MenuListFragment.newInstance(menuName), menuName);
        }
        viewPager.setAdapter(adapter);
    }
}
