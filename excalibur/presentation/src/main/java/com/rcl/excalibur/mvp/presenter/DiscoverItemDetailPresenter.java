package com.rcl.excalibur.mvp.presenter;


import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactory;
import com.rcl.excalibur.adapters.delegate.factory.DinningDetailModuleFactory;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.DiscoverItemDetailView;
import com.rcl.excalibur.utils.DetailModelProvider;

public class DiscoverItemDetailPresenter implements BasePresenter {

    private DiscoverItemDetailView view;
    private DetailModuleFactory moduleFactory;
    private DiscoverItemModel itemModel;

    public DiscoverItemDetailPresenter(DiscoverItemDetailView view, String discoverItemId) {
        this.view = view;
        itemModel = DetailModelProvider.discoverItemMap.get(discoverItemId); //TODO query database
        if (itemModel != null) {
            initModuleFactory();
            initView();
        } else {
            view.showToastAndFinishActivity("Discover Item Not Found");
        }
    }

    private void initModuleFactory() {
        String type = itemModel.getType();
        if (DiscoverItemModel.TYPE_DINING.equals(type)) {
            moduleFactory = new DinningDetailModuleFactory(itemModel);
        } else if (DiscoverItemModel.TYPE_SHOREX.equals(type)) {
            //TODO add more module factories;
            moduleFactory = null;
        }
    }

    private void initView() {
        AppCompatActivity activity = view.getActivity();
        if (activity != null) {
            view.setDetailTitle(itemModel.getTitle());
            view.setHeroImage(itemModel.getImageUrl());
            view.setAdapterObserver(new DetailAdapterObserver(this));
            if (moduleFactory != null) {
                view.render(moduleFactory.getDelegateAdapterArray(), moduleFactory.getListOfDetailViewTypes(activity.getResources()));
            } else {
                view.showToastAndFinishActivity("Discover Activity Not Recognized");
            }
        }
    }

    public void onBackClicked() {
        if (view.getActivity() != null) {
            view.getActivity().finish();
        }
    }


    private class DetailAdapterObserver extends DefaultPresentObserver<DiscoverItemModel, DiscoverItemDetailPresenter> {

        DetailAdapterObserver(DiscoverItemDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {
            //TODO do something when a detail item is clicked.
        }
    }
}
