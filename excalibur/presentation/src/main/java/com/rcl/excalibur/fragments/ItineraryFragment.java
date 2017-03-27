package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.itinerary.ItineraryPresenter;
import com.rcl.excalibur.mvp.view.itinerary.ItineraryView;

public class ItineraryFragment extends Fragment implements ItineraryView.OnRefreshDataListener {

    ItineraryPresenter presenter;

    public static ItineraryFragment newInstance() {
        return new ItineraryFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new ItineraryPresenter(new ItineraryView(this));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_itinerary, container, false);
    }

    @Override
    public void onRefresh() {
        presenter.refreshItinerary();
    }
}