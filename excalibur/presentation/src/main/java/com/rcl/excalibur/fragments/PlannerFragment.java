package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mapper.PlannerProductModelMapper;
import com.rcl.excalibur.mvp.presenter.PlannerPresenter;
import com.rcl.excalibur.mvp.view.PlannerView;

public class PlannerFragment extends Fragment {
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
                new GetProductDbUseCase(new ProductDataRepository()),
                new PlannerProductModelMapper(getResources())
        );
        presenter.init();
    }
}
