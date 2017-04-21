package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.preference.SailingPreferenceImpl;
import com.rcl.excalibur.data.repository.OfferingDataRepository;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.domain.interactor.GetOfferingsDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.preference.SailingPreferences;
import com.rcl.excalibur.mapper.PlannerProductModelMapper;
import com.rcl.excalibur.mvp.presenter.PlannerPresenter;
import com.rcl.excalibur.mvp.view.PlannerView;

import eu.davidea.flexibleadapter.FlexibleAdapter;

public class PlannerFragment extends BaseTripTychFragment implements FlexibleAdapter.OnItemClickListener {
    private PlannerPresenter presenter;
    private SailingPreferences sailingPreferences;

    public static PlannerFragment newInstance() {
        return new PlannerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_planner, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sailingPreferences = new SailingPreferenceImpl(getContext());
        presenter = new PlannerPresenter(
                new PlannerView(this),
                new GetOfferingsDbUseCase(new OfferingDataRepository()),
                new PlannerProductModelMapper(getResources()),
                new GetSailingPreferenceUseCase(sailingPreferences),
                new GetSaildDateDbUseCase(new SailDateDataRepository())
        );
        presenter.init();
    }

    @Override
    public boolean onItemClick(int position) {
        presenter.onItemClick(position);
        return true;
    }

    @Override
    public void onServiceCallCompleted(boolean success) {
        presenter.onServiceCallCompleted();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getArrivingDebarkingInfo();
    }
}
