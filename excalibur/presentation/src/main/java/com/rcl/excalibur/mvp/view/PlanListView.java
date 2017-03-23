package com.rcl.excalibur.mvp.view;


import android.support.v4.app.Fragment;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.ButterKnife;


public class PlanListView extends ActivityView<PlanListActivity> {

    public PlanListView(PlanListActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init(Fragment fragment) {
        callFragment(fragment);
    }

    private void callFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.full_content,
                fragment).commit();
    }
}
