package com.rcl.excalibur.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.DiscoverTabPresenter;
import com.rcl.excalibur.mvp.presenter.PlanListPresenter;
import com.rcl.excalibur.mvp.view.DiscoverTabView;

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
        presenter.openListScreen(PlanListPresenter.POSITION_DINING);
    }

    @OnClick(R.id.button_excursions)
    public void excursionsOnClick() {
        presenter.openListScreen(PlanListPresenter.POSITION_SHOREX);
    }

    @OnClick(R.id.button_spa)
    public void spaOnClick() {
        presenter.openListScreen(PlanListPresenter.POSITION_SPA);
    }

    @OnClick(R.id.button_shop)
    public void shopOnClick() {
        presenter.openListScreen(PlanListPresenter.POSITION_SHOPPING);
    }

    @OnClick(R.id.button_entertainment)
    public void entertainmentOnClick() {
        presenter.openListScreen(PlanListPresenter.POSITION_ENTERTAINMENT);
    }

    @OnClick(R.id.button_activities)
    public void activitiesOnClick() {
        presenter.openListScreen(PlanListPresenter.POSITION_ROYAL_ACTIVITY);
    }

    @OnClick(R.id.button_search)
    public void searchOnClick() {
        Toast.makeText(getActivity(), "Search Click", Toast.LENGTH_LONG).show();
    }

}