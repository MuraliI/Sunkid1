package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.repository.OfferingDataRepository;
import com.rcl.excalibur.domain.interactor.GetOfferingsDbUseCase;
import com.rcl.excalibur.custom.itinerary.RoyalLinearLayoutManager;
import com.rcl.excalibur.mapper.PlannerProductModelMapper;
import com.rcl.excalibur.mvp.presenter.PlannerPresenter;
import com.rcl.excalibur.mvp.view.PlannerView;

import eu.davidea.flexibleadapter.FlexibleAdapter;

public class PlannerFragment extends Fragment implements FlexibleAdapter.OnItemClickListener, RoyalLinearLayoutManager.OnFirstTimeListener {
    private PlannerPresenter presenter;

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
        presenter = new PlannerPresenter(
                new PlannerView(this),
                new GetOfferingsDbUseCase(new OfferingDataRepository()),
                new PlannerProductModelMapper(getResources())
        );
        presenter.init();
    }

    @Override
    public boolean onItemClick(int position) {
        presenter.onItemClick(position);
        return true;
    }

    @Override
    public void isShowingItems(int visibleItemCount) {
        presenter.isShowingItems(visibleItemCount);
    }
}
