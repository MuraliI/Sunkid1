package com.rcl.excalibur.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.data.service.DiscoverServicesImpl;
import com.rcl.excalibur.data.service.SailDateServicesImpl;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateUseCase;
import com.rcl.excalibur.mvp.presenter.DiscoverTabPresenter;
import com.rcl.excalibur.mvp.view.DiscoverTabView;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.rcl.excalibur.mvp.view.PlanListView.POSITION_DINING;
import static com.rcl.excalibur.mvp.view.PlanListView.POSITION_ENTERTAINMENT;
import static com.rcl.excalibur.mvp.view.PlanListView.POSITION_ROYAL_ACTIVITY;
import static com.rcl.excalibur.mvp.view.PlanListView.POSITION_SHOPPING;
import static com.rcl.excalibur.mvp.view.PlanListView.POSITION_SHOREX;
import static com.rcl.excalibur.mvp.view.PlanListView.POSITION_SPA;

public class DiscoverTabFragment extends Fragment {

    protected DiscoverTabPresenter presenter;

    public static DiscoverTabFragment newInstance() {
        return new DiscoverTabFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_discover_tab, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new DiscoverTabPresenter(new DiscoverTabView(this),
                    new GetProductsUseCase(new DiscoverServicesImpl(new ProductDataRepository())),
                    new GetSaildDateUseCase(new SailDateServicesImpl(new SailDateDataRepository())));
        presenter.init();
    }


    @OnClick(R.id.button_dinning)
    public void dinningOnClick() {
        presenter.openListScreen(POSITION_DINING);
    }

    @OnClick(R.id.button_excursions)
    public void excursionsOnClick() {
        presenter.openListScreen(POSITION_SHOREX);
    }

    @OnClick(R.id.button_spa)
    public void spaOnClick() {
        presenter.openListScreen(POSITION_SPA);
    }

    @OnClick(R.id.button_shop)
    public void shopOnClick() {
        presenter.openListScreen(POSITION_SHOPPING);
    }

    @OnClick(R.id.button_entertainment)
    public void entertainmentOnClick() {
        presenter.openListScreen(POSITION_ENTERTAINMENT);
    }

    @OnClick(R.id.button_activities)
    public void activitiesOnClick() {
        presenter.openListScreen(POSITION_ROYAL_ACTIVITY);
    }

    @OnClick(R.id.button_search)
    public void searchOnClick() {
        presenter.openDayPicker();
    }

    @OnClick(R.id.image_boat)
    public void boatOnClick() {
        presenter.boatOnClick();
    }

}
