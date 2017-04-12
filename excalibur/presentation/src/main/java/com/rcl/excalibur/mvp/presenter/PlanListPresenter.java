package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.PlanListView;
import com.rcl.excalibur.utils.ActivityUtils;

public class PlanListPresenter {
    private final PlanListView view;

    public PlanListPresenter(PlanListView view) {
        this.view = view;
    }

    public void init(int fragmentToShow) {
        view.setAdapterObserver(new AdapterObserver(this));
        view.init(fragmentToShow);
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    public class AdapterObserver extends DefaultPresentObserver<DiscoverItemModel, PlanListPresenter> {

        AdapterObserver(PlanListPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {
            //TODO Invoke Details screen
        }
    }

}
