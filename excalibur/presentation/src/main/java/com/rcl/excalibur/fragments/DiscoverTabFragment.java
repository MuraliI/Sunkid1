package com.rcl.excalibur.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.mvp.presenter.DiscoverTabPresenter;
import com.rcl.excalibur.mvp.view.DiscoverTabView;
import com.rcl.excalibur.mvp.view.PlanListView;
import com.rcl.excalibur.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiscoverTabFragment extends Fragment {

    private DiscoverTabPresenter presenter;

    public static DiscoverTabFragment newInstance() {
        return new DiscoverTabFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new DiscoverTabPresenter(new DiscoverTabView(this));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover_tab, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_dinning)
    public void dinningOnClick() {
        openListScreen(PlanListView.POSITION_DINING);
    }

    @OnClick(R.id.button_excursions)
    public void excursionsOnClick() {
        openListScreen(PlanListView.POSITION_SHOREX);
    }

    @OnClick(R.id.button_spa)
    public void spaOnClick() {
        openListScreen(PlanListView.POSITION_SPA);
    }

    @OnClick(R.id.button_shop)
    public void shopOnClick() {
        openListScreen(PlanListView.POSITION_SHOPPING);
    }

    @OnClick(R.id.button_entertainment)
    public void entertainmentOnClick() {
        openListScreen(PlanListView.POSITION_ENTERTAINMENT);
    }

    @OnClick(R.id.button_activities)
    public void activitiesOnClick() {
        openListScreen(PlanListView.POSITION_ROYAL_ACTIVITY);
    }

    @OnClick(R.id.button_services)
    public void servicesOnClick() {
        Toast.makeText(getActivity(), "Services Click", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button_search)
    public void searchOnClick() {
        Toast.makeText(getActivity(), "Search Click", Toast.LENGTH_LONG).show();
    }

    private void openListScreen(int fragmentToShow) {
        Intent intent = new Intent(getActivity(), PlanListActivity.class);
        intent.putExtra(PlanListActivity.EXTRA_FRAGMENT_TYPE, fragmentToShow);
        ActivityUtils.startActivity(getActivity(), intent);
    }
}
