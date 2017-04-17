package com.rcl.excalibur.mvp.view;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.adapters.ProductsCategoryAdapter;
import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.fragments.ProductsListFragment;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.rcl.excalibur.fragments.ProductsListFragment.DINING;
import static com.rcl.excalibur.fragments.ProductsListFragment.ENTERTAINMENT;
import static com.rcl.excalibur.fragments.ProductsListFragment.ROYAL_ACTIVITY;
import static com.rcl.excalibur.fragments.ProductsListFragment.SHOPPING;
import static com.rcl.excalibur.fragments.ProductsListFragment.SHOREX;
import static com.rcl.excalibur.fragments.ProductsListFragment.SPA;
import static com.rcl.excalibur.fragments.ProductsListFragment.newInstance;


public class PlanListView extends ActivityView<PlanListActivity, Void, DiscoverItemModel> {
    public static final int POSITION_ROYAL_ACTIVITY = 0;
    public static final int POSITION_DINING = 1;
    public static final int POSITION_SHOPPING = 2;
    public static final int POSITION_SPA = 3;
    public static final int POSITION_SHOREX = 4;
    public static final int POSITION_ENTERTAINMENT = 5;

    @Bind(R.id.plans_header_name) TextView plansHeaderName;
    @Bind(R.id.tab_layout_products_list_categories) TabLayout tabProductCategories;
    @Bind(R.id.full_content) ViewPager pagerFragment;

    public PlanListView(PlanListActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init(int fragmentToShow) {
        final PlanListActivity activity = getActivity();
        if (activity == null) {
            return;
        }

        setUpViewPagerProductCategories(fragmentToShow, pagerFragment, getCategoriesDummies(fragmentToShow));
        tabProductCategories.setupWithViewPager(pagerFragment, true);
    }

    private void setUpViewPagerProductCategories(int fragmentToShow, ViewPager viewPager, List<String> categories) {
        ProductsCategoryAdapter adapter = new ProductsCategoryAdapter(getFragmentManager());
        for (String category : categories) {
            //adapter.addFragment(createFragment(fragmentToShow), category);
            adapter.addFragment(ProductsListFragment.newInstance(ROYAL_ACTIVITY), category);
        }
        //adapter.addFragment(new ProductsListFragment(), "One");
        viewPager.setAdapter(adapter);
    }

    private Fragment createFragment(int fragmentToShow) {
        Fragment fragment = null;
        //Objects needed for Analytics tracking
        AnalyticEvent analyticEvent = new AnalyticEvent(AnalyticsConstants.KEY_FILTER_DISCOVER);
        String categorySelected = "";
        switch (fragmentToShow) {
            case POSITION_ROYAL_ACTIVITY:
                fragment = newInstance(ROYAL_ACTIVITY);
                categorySelected = getActivity().getString(R.string.royal_activity);
                break;
            case POSITION_DINING:
                fragment = newInstance(DINING);
                categorySelected = getActivity().getString(R.string.dining);
                break;
            case POSITION_SHOPPING:
                fragment = newInstance(SHOPPING);
                categorySelected = getActivity().getString(R.string.shopping);
                break;
            case POSITION_SPA:
                fragment = newInstance(SPA);
                categorySelected = getActivity().getString(R.string.spa);
                break;
            case POSITION_SHOREX:
                fragment = newInstance(SHOREX);
                categorySelected = getActivity().getString(R.string.shorex);
                break;
            case POSITION_ENTERTAINMENT:
                fragment = newInstance(ENTERTAINMENT);
                categorySelected = getActivity().getString(R.string.entertainment);
                break;
            default:
                Preconditions.unreachable();
        }

        plansHeaderName.setText(categorySelected);
        AnalyticsUtils.trackEvent(analyticEvent.addKeyValue(AnalyticsConstants.KEY_FILTER_CATEGORY, categorySelected));
        return fragment;
    }

    private List<String> getCategoriesDummies(int fragmentToShow) {
        List<String> categoriesDommies = new ArrayList<>();
        switch (fragmentToShow) {
            case POSITION_ROYAL_ACTIVITY:
                categoriesDommies.add("Facial");
                categoriesDommies.add("Hair");
                categoriesDommies.add("Manicure");
                break;
            case POSITION_DINING:
                break;
            case POSITION_SHOPPING:
                categoriesDommies.add("Message");
                categoriesDommies.add("Facial");
                break;
            case POSITION_SPA:
                categoriesDommies.add("Message");
                categoriesDommies.add("Facial");
                categoriesDommies.add("Hair");
                categoriesDommies.add("Manicure");
                break;
            case POSITION_SHOREX:
                categoriesDommies.add("Message");
                break;
            case POSITION_ENTERTAINMENT:
                categoriesDommies.add("Message");
                categoriesDommies.add("Facial");
                categoriesDommies.add("Hair");
                categoriesDommies.add("Manicure");
                categoriesDommies.add("Manicure");
                categoriesDommies.add("Manicure");
                break;
            default:
        }
        return categoriesDommies;
    }
}
