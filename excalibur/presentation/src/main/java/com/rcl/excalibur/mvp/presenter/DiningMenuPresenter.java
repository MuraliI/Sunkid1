package com.rcl.excalibur.mvp.presenter;

import android.support.v4.view.ViewPager;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.DiningMenuPagerAdapter;
import com.rcl.excalibur.data.repository.SubCategoriesDataRepository;
import com.rcl.excalibur.domain.SubCategory;
import com.rcl.excalibur.domain.interactor.GetSubCategoryDbUseCase;
import com.rcl.excalibur.fragments.MenuListFragment;
import com.rcl.excalibur.mvp.view.DiningMenuView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import timber.log.Timber;

public class DiningMenuPresenter {

    private final DiningMenuView view;
    private String[] dummieTitles = {"Breakfast", "Lunch", "Dinner", "Vegetarian", "Dessert"};

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

    //TODO: Information from DB
    public void createFragmentMenu(String idTypeMenu, ViewPager viewPager) {
        AnalyticEvent analyticEvent = new AnalyticEvent(AnalyticsConstants.KEY_FILTER_DISCOVER);
        GetSubCategoryDbUseCase getSubCategoryDbUseCase = new GetSubCategoryDbUseCase(new SubCategoriesDataRepository());
        SubCategory subCategory = getSubCategoryDbUseCase.get(idTypeMenu);
        //if (subCategory != null) {
        addFragmentsToPager(subCategory, viewPager, idTypeMenu);
        AnalyticsUtils.trackEvent(analyticEvent.addKeyValue(AnalyticsConstants.KEY_FILTER_CATEGORY, idTypeMenu));
        //} else {
        //    Timber.e("Don't find information for menu");
        //}
    }

    //TODO: Information from DB
    private void addFragmentsToPager(SubCategory subCategory, ViewPager viewPager, String idTypeMenu) {
        DiningMenuPagerAdapter adapter = new DiningMenuPagerAdapter(view.getFragmentManager());
        //List<ChildCategory> childCategories = subCategory.getChildCategory();
        //if (childCategories.isEmpty()) {
        Timber.e("Don't find menu information ");
        //} else {
            /*for (ChildCategory childCategory : childCategories) {
                String categoryName = childCategory.getItems().getCategoryName();
                adapter.addFragment(MenuListFragment.newInstance(idTypeMenu), categoryName);
            }*/
        for (String dummieTitle : dummieTitles) {
            String categoryName = dummieTitle;
            adapter.addFragment(MenuListFragment.newInstance(idTypeMenu), dummieTitle);
        }

        //}
        viewPager.setAdapter(adapter);
    }
}
