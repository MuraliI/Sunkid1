package com.rcl.excalibur.mvp.view;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;
import com.rcl.excalibur.utils.ActivityUtils;

public class DiscoverTabView extends FragmentView<DiscoverTabFragment, Void, Void> {


    private String SHOREX = "shorex";
    private String ACYIVITY = "ACT";
    private String SHOPPING = "Shopping";
    private String SPA = "Spa";
    private String DINING = "dining";
    private String ENTRETAINMENT = "ent";

    public DiscoverTabView(DiscoverTabFragment fragment) {
        super(fragment);
    }

    public void openListScreen(int fragmentToShow) {
        BaseActivity activity = getActivity();
        if (activity == null) {
            return;
        }

        String category = null;

        switch (fragmentToShow) {
            case PlanListView.POSITION_ROYAL_ACTIVITY:
                category = ACYIVITY;
                break;
            case PlanListView.POSITION_DINING:
                category = DINING;
                break;
            case PlanListView.POSITION_SHOPPING:
                category = SHOPPING;
                break;
            case PlanListView.POSITION_SPA:
                category = SPA;
                break;
            case PlanListView.POSITION_SHOREX:
                category = SHOREX;
                break;
            case PlanListView.POSITION_ENTERTAINMENT:
                category = ENTRETAINMENT;
                break;
            default:
                Preconditions.unreachable();

        }

        if (category != null) {
            ActivityUtils.startActivity(activity, PlanListActivity.getStartIntent(activity, fragmentToShow));
        }
    }
}

