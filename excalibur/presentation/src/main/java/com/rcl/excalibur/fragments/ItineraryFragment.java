package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.internal.di.component.FragmentComponent;
import com.rcl.excalibur.internal.di.component.itinerary.ItineraryFragmentComponent;
import com.rcl.excalibur.internal.di.module.itinerary.ItineraryFragmentModule;
import com.rcl.excalibur.mvp.presenter.itinerary.ItineraryPresenter;
import com.rcl.excalibur.mvp.view.itinerary.ItineraryView;

public class ItineraryFragment extends BaseFragment<ItineraryPresenter> implements ItineraryView.OnRefreshDataListener {
    private ItineraryFragmentComponent itineraryFragmentComponent;

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
        presenter.init();
    }

    @Override
    public void onRefresh() {
        presenter.refreshItinerary();
    }

    @Override
    protected void createFragmentComponent() {
        application = ((RCLApp) getActivity().getApplication());
        application.createItineraryComponent();
        itineraryFragmentComponent = application.getItineraryComponent().plus(new ItineraryFragmentModule(this));
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        itineraryFragmentComponent.inject(this);
    }

    @Override
    protected void destroyFragmentComponent() {
        itineraryFragmentComponent = null;
        application.destroyItineraryComponent();
    }
}
