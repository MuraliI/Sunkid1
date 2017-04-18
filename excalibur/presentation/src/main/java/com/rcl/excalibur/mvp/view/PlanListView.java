package com.rcl.excalibur.mvp.view;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.adapters.ProductsCategoryAdapter;
import com.rcl.excalibur.data.repository.SubCategoriesDataRepository;
import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.domain.ChildCategory;
import com.rcl.excalibur.domain.SubCategory;
import com.rcl.excalibur.domain.interactor.GetSubCategoryDbUseCase;
import com.rcl.excalibur.fragments.ProductsListFragment;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.rcl.excalibur.fragments.ProductsListFragment.DINING;
import static com.rcl.excalibur.fragments.ProductsListFragment.ENTERTAINMENT;
import static com.rcl.excalibur.fragments.ProductsListFragment.ROYAL_ACTIVITY;
import static com.rcl.excalibur.fragments.ProductsListFragment.SHOPPING;
import static com.rcl.excalibur.fragments.ProductsListFragment.SHOREX;
import static com.rcl.excalibur.fragments.ProductsListFragment.SPA;


public class PlanListView extends ActivityView<PlanListActivity, Void, DiscoverItemModel> {
    public static final int POSITION_ROYAL_ACTIVITY = 0;
    public static final int POSITION_DINING = 1;
    public static final int POSITION_SHOPPING = 2;
    public static final int POSITION_SPA = 3;
    public static final int POSITION_SHOREX = 4;
    public static final int POSITION_ENTERTAINMENT = 5;

    private static final String ID_SHOREX = "shorex";
    private static final String ID_ACYIVITY = "ACT";
    private static final String ID_SHOPPING = "shop";
    private static final String ID_SPA = "spa";
    private static final String ID_DINING = "dining";
    private static final String ID_ENTRETAINMENT = "ent";

    @Bind(R.id.plans_header_name) TextView plansHeaderName;
    @Bind(R.id.tab_layout_products_list_categories) TabLayout tabProductCategories;
    @Bind(R.id.full_content) ViewPager pagerFragment;
    @Bind(R.id.category_icon) ImageView categoryIcon;

    public PlanListView(PlanListActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init(int fragmentToShow) {
        final PlanListActivity activity = getActivity();
        if (activity == null) {
            return;
        }

        createFragment(fragmentToShow, pagerFragment);
        tabProductCategories.setupWithViewPager(pagerFragment, true);
    }

    private void createFragment(int fragmentToShow, ViewPager viewPager) {
        AnalyticEvent analyticEvent = new AnalyticEvent(AnalyticsConstants.KEY_FILTER_DISCOVER);
        String categorySelected = null;
        String idCategory = null;
        int type = -1;

        switch (fragmentToShow) {
            case POSITION_ROYAL_ACTIVITY:
                type = ROYAL_ACTIVITY;
                categorySelected = getActivity().getString(R.string.royal_activity);
                idCategory = ID_ACYIVITY;
                categoryIcon.setImageResource(R.drawable.ic_dining_color);
                break;
            case POSITION_DINING:
                type = DINING;
                categorySelected = getActivity().getString(R.string.dining);
                idCategory = ID_DINING;
                categoryIcon.setImageResource(R.drawable.ic_dining_color);
                break;
            case POSITION_SHOPPING:
                type = SHOPPING;
                categorySelected = getActivity().getString(R.string.shopping);
                idCategory = ID_SHOPPING;
                categoryIcon.setImageResource(R.drawable.ic_shops_color);
                break;
            case POSITION_SPA:
                type = SPA;
                categorySelected = getActivity().getString(R.string.spa);
                idCategory = ID_SPA;
                categoryIcon.setImageResource(R.drawable.ic_spa_color);
                break;
            case POSITION_SHOREX:
                type = SHOREX;
                categorySelected = getActivity().getString(R.string.shorex);
                idCategory = ID_SHOREX;
                categoryIcon.setImageResource(R.drawable.ic_excrusions_color);
                break;
            case POSITION_ENTERTAINMENT:
                type = ENTERTAINMENT;
                categorySelected = getActivity().getString(R.string.entertainment);
                idCategory = ID_ENTRETAINMENT;
                categoryIcon.setImageResource(R.drawable.ic_entertainment_color);
                break;
            default:
                Preconditions.unreachable();
        }

        plansHeaderName.setText(categorySelected);
        GetSubCategoryDbUseCase getSubCategoryDbUseCase = new GetSubCategoryDbUseCase(new SubCategoriesDataRepository());
        SubCategory subCategory = getSubCategoryDbUseCase.get(idCategory);
        if (subCategory != null) {
            addFragmentsToPager(subCategory, viewPager, type);
            AnalyticsUtils.trackEvent(analyticEvent.addKeyValue(AnalyticsConstants.KEY_FILTER_CATEGORY, categorySelected));
        } else {
            Timber.e("Don't find information for category");
        }
    }

    public void addFragmentsToPager(SubCategory subCategory, ViewPager viewPager, int type) {
        ProductsCategoryAdapter adapter = new ProductsCategoryAdapter(getFragmentManager());
        List<ChildCategory> childCategories = subCategory.getChildCategory();
        if (childCategories.isEmpty()) {
            tabProductCategories.setVisibility(View.GONE);
            adapter.addFragment(ProductsListFragment.newInstance(type, null), null);
        } else {
            for (ChildCategory childCategory : childCategories) {
                String categoryName = childCategory.getItems().getCategoryName();
                adapter.addFragment(ProductsListFragment.newInstance(type, categoryName), categoryName);
            }
        }
        viewPager.setAdapter(adapter);
    }
}

