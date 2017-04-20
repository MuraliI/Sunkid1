package com.rcl.excalibur.mvp.view;

import android.view.View;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;
import com.rcl.excalibur.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscoverTabView extends FragmentView<DiscoverTabFragment, Void, Void> {

    @Bind(R.id.button_dinning) View dinningButton;
    @Bind(R.id.button_excursions) View excursionsButton;
    @Bind(R.id.button_spa) View spaButton;
    @Bind(R.id.button_shop) View shopButton;
    @Bind(R.id.button_entertainment) View entertainmentButton;
    @Bind(R.id.button_activities) View activitiesButton;
    @Bind(R.id.button_guest_services) View guestButton;
    @Bind(R.id.progress_service_call_discover) View progressBar;

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

    public void init() {
        ButterKnife.bind(this, getActivity());
        enableDisableCategoryButtons(false);
    }

    public void showLoadingView(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void serviceCallCompleted() {
        enableDisableCategoryButtons(true);
    }

    private void enableDisableCategoryButtons(boolean enable) {
        dinningButton.setEnabled(enable);
        excursionsButton.setEnabled(enable);
        spaButton.setEnabled(enable);
        shopButton.setEnabled(enable);
        entertainmentButton.setEnabled(enable);
        activitiesButton.setEnabled(enable);
        guestButton.setEnabled(enable);
    }
}

