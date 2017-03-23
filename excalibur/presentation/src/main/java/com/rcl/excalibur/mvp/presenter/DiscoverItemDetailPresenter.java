package com.rcl.excalibur.mvp.presenter;


import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DiscoverDeckMapActivity;
import com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactory;
import com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactoryProvider;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.DiscoverItemDetailView;

import static com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactoryProvider.TYPE_SHOPPING;

public class DiscoverItemDetailPresenter implements BasePresenter {
    private DiscoverItemDetailView view;
    private DetailModuleFactory moduleFactory;
    // TODO: 22/03/17 Change it to Product
    private DiscoverItemModel itemModel;

    public DiscoverItemDetailPresenter(DiscoverItemDetailView view, long productId) {
        this.view = view;
        // TODO: 22/03/17 Query the database
        itemModel = new DiscoverItemModel();
        DetailModuleFactoryProvider factoryProvider = new DetailModuleFactoryProvider();
        if (itemModel != null) {
            if (TYPE_SHOPPING.equals(itemModel.getType())) {
                view.showOnlyReservationIcon();
            }
            moduleFactory = factoryProvider.getFactory(itemModel.getType());
            if (moduleFactory == null) {
                view.showToastAndFinishActivity("Discover Item Not Found");
            } else {
                moduleFactory.setItemModel(itemModel);
                initView();
            }
        } else {
            view.showToastAndFinishActivity("Discover Item Not Found");
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

    public void onDeckMapClicked() {
        BaseActivity activity = (BaseActivity) view.getActivity();
        if (activity != null) {
            activity.startActivity(DiscoverDeckMapActivity.getIntent(activity, itemModel.getDiscoverId()));
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
