package com.rcl.excalibur.mvp.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ProgressBar;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.DiningMenuActivity;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiningMenuView extends ActivityView<DiningMenuActivity, Void, Void> {

    @BindView(R.id.tab_layout_menus) TabLayout tabProductMenus;
    @BindView(R.id.full_content) ViewPager pagerFragment;
    @BindView(R.id.progress_content) ProgressBar progressBar;

    public DiningMenuView(DiningMenuActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init() {
        final DiningMenuActivity activity = getActivity();
        if (activity != null) {
            showProgressBar();
            activity.presenter.createFragmentMenu(pagerFragment);
            tabProductMenus.setupWithViewPager(pagerFragment, true);
        }
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
