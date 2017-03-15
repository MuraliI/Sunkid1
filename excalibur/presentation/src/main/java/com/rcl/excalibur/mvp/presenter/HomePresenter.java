package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.HomeActivity;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.DiscoverItemDbUseCase;
import com.rcl.excalibur.domain.interactor.GetLisDiscoverItemsUserCase;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.rcl.excalibur.utils.ActivityUtils.startActivity;

public class HomePresenter implements BasePresenter {

    @Inject GetLisDiscoverItemsUserCase getLisDiscoverItemsUserCase;
    @Inject DiscoverItemDbUseCase discoverItemDbUseCase;

    private HomeView view;

    public HomePresenter(HomeView view) {
        this.view = view;
        init();
    }

    private void init() {
        view.setAdapterObserver(new AdapterObserver(this));
        view.init();
//        TODO hard. waiting mock service
        List<DiscoverItemModel> discoverItemModels = new ArrayList();
        discoverItemModels.add(new DiscoverItemModel());
        view.addAll(discoverItemModels);
        initInjection();
//        Init data in DB
        getLisDiscoverItemsUserCase.execute(new DiscoverListObserver(), null);
    }

    private void initInjection() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
    }

    public class AdapterObserver extends DefaultPresentObserver<DiscoverItemModel, HomePresenter> {

        public AdapterObserver(HomePresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {
            final HomeActivity activity = getPresenter().view.getActivity();
            if (activity == null) {
                return;
            }

            startActivity(activity, PlanListActivity.getStartIntent(activity));
        }
    }

    public final class DiscoverListObserver extends DefaultObserver<List<DiscoverItem>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(List<DiscoverItem> discoverItems) {
            discoverItemDbUseCase.create(discoverItems);
        }
    }
}
