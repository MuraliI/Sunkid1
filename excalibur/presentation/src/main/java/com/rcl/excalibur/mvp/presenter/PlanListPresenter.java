package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.PlanListView;
import com.rcl.excalibur.utils.ActivityUtils;

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

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    public class AdapterObserver extends DefaultPresentObserver<DiscoverItemModel, PlanListPresenter> {

        public AdapterObserver(PlanListPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {

//   TODO         Invoke Details screen
        }
    }

}
