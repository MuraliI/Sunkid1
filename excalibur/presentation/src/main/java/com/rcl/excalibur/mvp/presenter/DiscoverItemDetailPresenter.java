package com.rcl.excalibur.mvp.presenter;


import android.content.res.Resources;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactory;
import com.rcl.excalibur.adapters.delegate.factory.DinningDetailModuleFactory;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.DiscoverItemDetailView;

public class DiscoverItemDetailPresenter implements BasePresenter {

    private DiscoverItemDetailView view;
    private DetailModuleFactory moduleFactory;

    public DiscoverItemDetailPresenter(DiscoverItemDetailView view, DiscoverItemModel discoverItemModel) {
        this.view = view;
        initModuleFactory(discoverItemModel);
        initView();
    }

    //TODO check what kind of plan was passed by the activity
    private void initModuleFactory(DiscoverItemModel discoverItemModel) {
        moduleFactory = new DinningDetailModuleFactory(discoverItemModel);
    }

    private void initView() {
        Resources resources = null;
        if (view.getActivity() != null) {
            view.setDetailTitle(view.getActivity().getString(R.string.hardcoded_activity_title)); //FIXME get the plan title
            resources = view.getActivity().getResources();
        }
        view.setAdapterObserver(new DetailAdapterObserver(this));
        view.render(moduleFactory.getDelegateAdapterArray(), moduleFactory.getListOfDetailViewTypes(resources));
    }



    public class DetailAdapterObserver extends DefaultPresentObserver<DiscoverItemModel, DiscoverItemDetailPresenter> {

        public DetailAdapterObserver(DiscoverItemDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {
            //TODO do something when a detail item is clicked.
        }
    }
}
