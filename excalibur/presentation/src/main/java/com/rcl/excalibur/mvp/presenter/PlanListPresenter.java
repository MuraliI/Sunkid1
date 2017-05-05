package com.rcl.excalibur.mvp.presenter;


import android.support.v4.view.ViewPager;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.ProductsCategoryAdapter;
import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.domain.ChildCategory;
import com.rcl.excalibur.domain.SubCategory;
import com.rcl.excalibur.fragments.ProductsListFragment;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.presenter.rx.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.PlanListView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;

import java.util.List;

import static com.rcl.excalibur.fragments.ProductsListFragment.DINING;
import static com.rcl.excalibur.fragments.ProductsListFragment.ENTERTAINMENT;
import static com.rcl.excalibur.fragments.ProductsListFragment.GUEST_SERVICES;
import static com.rcl.excalibur.fragments.ProductsListFragment.ROYAL_ACTIVITY;
import static com.rcl.excalibur.fragments.ProductsListFragment.SHOPPING;
import static com.rcl.excalibur.fragments.ProductsListFragment.SHOREX;
import static com.rcl.excalibur.fragments.ProductsListFragment.SPA;

public class PlanListPresenter {
    public static final int POSITION_ROYAL_ACTIVITY = 0;
    public static final int POSITION_DINING = 1;
    public static final int POSITION_SHOPPING = 2;
    public static final int POSITION_SPA = 3;
    public static final int POSITION_SHOREX = 4;
    public static final int POSITION_ENTERTAINMENT = 5;
    public static final int POSITION_GUEST_SERVICES = 6;

    private static final String ID_SHOREX = "shoreex";
    private static final String ID_ACTIVITY = "activities";
    private static final String ID_SHOPPING = "shop";
    private static final String ID_SPA = "spa";
    private static final String ID_DINING = "dining";
    private static final String ID_ENTERTAINMENT = "entertainment";
    private static final String ID_GUEST_SERVICES = "guest";

    private final PlanListView view;

    public PlanListPresenter(PlanListView view) {
        this.view = view;
    }

    public void init(int fragmentToShow) {
        view.setAdapterObserver(new AdapterObserver(this));
        view.init(fragmentToShow);
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    public String createFragment(int fragmentToShow, ViewPager viewPager) {
        AnalyticEvent analyticEvent = new AnalyticEvent(AnalyticsConstants.KEY_FILTER_DISCOVER);
        String categorySelected = null;
        String idCategory = null;
        int type = -1;
        int iconCategory = 0;

        switch (fragmentToShow) {
            case POSITION_ROYAL_ACTIVITY:
                type = ROYAL_ACTIVITY;
                categorySelected = view.getActivity().getString(R.string.royal_activity);
                idCategory = ID_ACTIVITY;
                iconCategory = R.drawable.icon_services_color;
                break;
            case POSITION_DINING:
                type = DINING;
                categorySelected = view.getActivity().getString(R.string.dining);
                idCategory = ID_DINING;
                iconCategory = R.drawable.ic_dining_color;
                break;
            case POSITION_SHOPPING:
                type = SHOPPING;
                categorySelected = view.getActivity().getString(R.string.shopping);
                idCategory = ID_SHOPPING;
                iconCategory = R.drawable.ic_shops_color;
                break;
            case POSITION_SPA:
                type = SPA;
                categorySelected = view.getActivity().getString(R.string.spa);
                idCategory = ID_SPA;
                iconCategory = R.drawable.ic_spa_color;
                break;
            case POSITION_SHOREX:
                type = SHOREX;
                categorySelected = view.getActivity().getString(R.string.shorex);
                idCategory = ID_SHOREX;
                iconCategory = R.drawable.ic_excrusions_color;
                break;
            case POSITION_ENTERTAINMENT:
                type = ENTERTAINMENT;
                categorySelected = view.getActivity().getString(R.string.entertainment);
                idCategory = ID_ENTERTAINMENT;
                iconCategory = R.drawable.ic_entertainment_color;
                break;
            case POSITION_GUEST_SERVICES:
                type = GUEST_SERVICES;
                categorySelected = view.getActivity().getString(R.string.services);
                idCategory = ID_GUEST_SERVICES;
                iconCategory = R.drawable.ic_guest_services_color;
                break;
            default:
                Preconditions.unreachable();
        }

        view.setCategoryIcon(iconCategory);
//TODO
//        GetSubCategoryDbUseCase getSubCategoryDbUseCase = new GetSubCategoryDbUseCase(new CategoriesDataRepository());
//        SubCategory subCategory = getSubCategoryDbUseCase.get(idCategory);
//        if (subCategory != null) {
//            addFragmentsToPager(subCategory, viewPager, type);
//            AnalyticsUtils.trackEvent(analyticEvent.addKeyValue(AnalyticsConstants.KEY_FILTER_CATEGORY, categorySelected));
//        } else {
//            Timber.e("Don't find information for category");
//        }

        return categorySelected;
    }

    private void addFragmentsToPager(SubCategory subCategory, ViewPager viewPager, int type) {
        ProductsCategoryAdapter adapter = new ProductsCategoryAdapter(view.getFragmentManager());
        List<ChildCategory> childCategories = subCategory.getChildCategory();
        if (childCategories.isEmpty()) {
            view.tabCategoriesVisibility();
            adapter.addFragment(ProductsListFragment.newInstance(type, null), null);
        } else {
            for (ChildCategory childCategory : childCategories) {
                String categoryId = childCategory.getItems().getCategoryId();
                String categoryName = childCategory.getItems().getCategoryName();
                adapter.addFragment(ProductsListFragment.newInstance(type, categoryId), categoryName);
            }
        }
        viewPager.setAdapter(adapter);
    }

    public class AdapterObserver extends DefaultPresentObserver<DiscoverItemModel, PlanListPresenter> {

        AdapterObserver(PlanListPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {
            //TODO Invoke Details screen
        }
    }
}
