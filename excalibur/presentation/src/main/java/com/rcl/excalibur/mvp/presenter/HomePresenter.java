package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.activity.HomeActivity;
import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.utils.ActivityUtils.startActivity;

public class HomePresenter implements BasePresenter {

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

            startActivity(activity, TriptychHomeActivity.getStartIntent(activity));
        }
    }
}
