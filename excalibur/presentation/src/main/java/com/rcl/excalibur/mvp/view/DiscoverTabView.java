package com.rcl.excalibur.mvp.view;

import android.content.Intent;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.utils.ActivityUtils;

import butterknife.ButterKnife;

public class DiscoverTabView extends FragmentView<DiscoverTabFragment> {

    public DiscoverTabView(DiscoverTabFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void openListScreen(int fragmentToShow) {
        BaseActivity activity = getActivity();
        Intent intent = PlanListActivity.getStartIntent(activity);
        intent.putExtra(PlanListActivity.EXTRA_FRAGMENT_TYPE, fragmentToShow);
        ActivityUtils.startActivity(activity, intent);
    }
}
