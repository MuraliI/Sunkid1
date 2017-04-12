package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.service.ItineraryServiceImpl;
import com.rcl.excalibur.model.itinerary.ItineraryProductModelMapper;
import com.rcl.excalibur.mvp.presenter.itinerary.ItineraryPresenter;
import com.rcl.excalibur.mvp.view.itinerary.ItineraryView;

public class ItineraryFragment extends Fragment implements ItineraryView.OnRefreshDataListener {
    private ItineraryPresenter presenter;

    public static ItineraryFragment newInstance() {
        return new ItineraryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_itinerary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new ItineraryPresenter(new ItineraryView(this)
                , new ItineraryServiceImpl()
                , new ItineraryProductModelMapper(getResources()));
        presenter.init();
    }

    @Override
    public void onRefresh() {
        presenter.refreshItinerary();
    }
}
