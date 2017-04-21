package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
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
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.mvp.presenter.PlannerPresenter;
import com.rcl.excalibur.mvp.view.PlannerView;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import io.reactivex.functions.Consumer;

public class PlannerFragment extends BaseTripTychFragment implements FlexibleAdapter.OnItemClickListener, Consumer<Pair<Integer, Integer>> {
    private PlannerPresenter presenter;
    private PlannerView plannerView;
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
        plannerView = new PlannerView(this);
        sailingPreferences = new SailingPreferenceImpl(getContext());
        presenter = new PlannerPresenter(
                plannerView,
                new GetOfferingsDbUseCase(new OfferingDataRepository()),
                new PlannerProductModelMapper(getResources()),
                new GetSailingPreferenceUseCase(sailingPreferences),
                new GetSaildDateDbUseCase(new SailDateDataRepository()),
                new SailingInformationModelDataMapper()
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

    public void onResume() {
        super.onResume();
        presenter.getArrivingDebarkingInfo();
    }

    @Override
    public void accept(Pair<Integer, Integer> integerIntegerPair) throws Exception {
        if (plannerView != null) {
            plannerView.setShipInvisibleHeight(integerIntegerPair);
        }
    }
}
