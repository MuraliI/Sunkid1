package com.rcl.excalibur.mvp.view;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PlanListView extends ActivityView<PlanListActivity, Void, DiscoverItemModel> {

    @Bind(R.id.plans_header_name) TextView plansHeaderName;
    @Bind(R.id.toolbar_header) Toolbar toolbar;
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
        String productType = activity.presenter.createFragment(fragmentToShow, pagerFragment);
        plansHeaderName.setText(productType);
        tabProductCategories.setupWithViewPager(pagerFragment, true);
    }

    public void setCategoryIcon(int categoryIconResource) {
        categoryIcon.setImageResource(categoryIconResource);
    }

    public void tabCategoriesVisibility() {
        tabProductCategories.setVisibility(View.GONE);
    }
}

