package com.rcl.excalibur.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.DiscoverTabPresenter;
import com.rcl.excalibur.mvp.view.DiscoverTabView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiscoverTabFragment extends Fragment {

    private DiscoverTabPresenter presenter;

    public static DiscoverTabFragment newInstance() {
        DiscoverTabFragment fragment = new DiscoverTabFragment();
        return fragment;
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
        Toast.makeText(getActivity(), "Dinning Click", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button_excursions)
    public void excursionsOnClick() {
        Toast.makeText(getActivity(), "Excursions Click", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button_spa)
    public void spaOnClick() {
        Toast.makeText(getActivity(), "Spa Click", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button_shop)
    public void shopOnClick() {
        Toast.makeText(getActivity(), "Shop Click", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button_entertainment)
    public void entertainmentOnClick() {
        Toast.makeText(getActivity(), "Entertainment Click", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button_activities)
    public void activitiesOnClick() {
        Toast.makeText(getActivity(), "Activities Click", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button_services)
    public void servicesOnClick() {
        Toast.makeText(getActivity(), "Services Click", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button_search)
    public void searchOnClick() {
        Toast.makeText(getActivity(), "Search Click", Toast.LENGTH_LONG).show();
    }
}
