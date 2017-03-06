package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.model.DiscoverModel;
import com.rcl.excalibur.mvp.view.PlanListView;

public class PlanListPresenter implements BasePresenter {
    private PlanListView view;

    public PlanListPresenter(PlanListView view) {
        this.view = view;
        init();
    }

    private void init() {
        view.setAdapterObserver(new AdapterObserver(this));
        view.init();
    }

    public class AdapterObserver extends DefaultPresentObserver<DiscoverModel, PlanListPresenter> {

        public AdapterObserver(PlanListPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverModel value) {

//   TODO         Invoke Details screen
        }
    }

}
