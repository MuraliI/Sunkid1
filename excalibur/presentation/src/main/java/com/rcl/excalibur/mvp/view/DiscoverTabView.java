package com.rcl.excalibur.mvp.view;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;
import com.rcl.excalibur.utils.ActivityUtils;

public class DiscoverTabView extends FragmentView<DiscoverTabFragment, Void, Void> {

    public DiscoverTabView(DiscoverTabFragment fragment) {
        super(fragment);
    }

    public void openListScreen(int fragmentToShow) {
        BaseActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.startActivity(activity, PlanListActivity.getStartIntent(activity, fragmentToShow));
    }
}

