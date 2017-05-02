package com.rcl.excalibur.mvp.view;

import android.view.View;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DiningMenuActivity;
import com.rcl.excalibur.data.repository.MenuDataRepository;
import com.rcl.excalibur.data.service.MenuServicesImpl;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;
import com.rcl.excalibur.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverTabView extends FragmentView<DiscoverTabFragment, Void, Void> {

    @BindView(R.id.button_dinning) View dinningButton;
    @BindView(R.id.button_excursions) View excursionsButton;
    @BindView(R.id.button_spa) View spaButton;
    @BindView(R.id.button_shop) View shopButton;
    @BindView(R.id.button_entertainment) View entertainmentButton;
    @BindView(R.id.button_activities) View activitiesButton;
    @BindView(R.id.button_guest_services) View guestButton;
    @BindView(R.id.progress_service_call_discover) View progressBar;

    public DiscoverTabView(DiscoverTabFragment fragment) {
        super(fragment);
    }

    public void openListScreen(int fragmentToShow) {
        BaseActivity activity = getActivity();
        if (activity == null) {
            return;
        }

        //TODO: Change clic
        new MenuServicesImpl(new MenuDataRepository()).getMenu();

        //ActivityUtils.startActivity(activity, PlanListActivity.getStartIntent(activity, fragmentToShow));
        ActivityUtils.startActivity(activity, DiningMenuActivity.getStartIntent(activity, "GIOV"));
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

