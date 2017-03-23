package com.rcl.excalibur.mvp.presenter;


import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactory;
import com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactoryProvider;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.DiscoverItemDetailView;

import javax.inject.Inject;

import static com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactoryProvider.TYPE_SHOPPING;

public class DiscoverItemDetailPresenter implements BasePresenter {
    @Inject GetProductDbUseCase getProductDbUseCase;
    private DiscoverItemDetailView view;
    private DetailModuleFactory moduleFactory;
    private Product product;

    public DiscoverItemDetailPresenter(DiscoverItemDetailView view, long productId) {
        this.view = view;
        initInjection();
        product = getProductDbUseCase.get(productId);
        DetailModuleFactoryProvider factoryProvider = new DetailModuleFactoryProvider();
        if (product != null) {
            String productTypeName = product.getProductType().getProductType();
            if (TYPE_SHOPPING.equals(productTypeName)) {
                view.showOnlyReservationIcon();
            }
            moduleFactory = factoryProvider.getFactory(productTypeName);
            if (moduleFactory == null) {
                view.showToastAndFinishActivity("Discover Item Not Found");
            } else {
                moduleFactory.setProduct(product);
                initView();
            }
        } else {
            view.showToastAndFinishActivity("Discover Item Not Found");
        }
    }

    private void initView() {
        AppCompatActivity activity = view.getActivity();
        if (activity != null) {
            view.setDetailTitle(product.getProductTitle());
            view.setHeroImage(product.getProductMedia().getMediaItem().get(0).getMediaRefLink());
            view.setAdapterObserver(new DetailAdapterObserver(this));
            if (moduleFactory != null) {
                view.render(moduleFactory.getDelegateAdapterArray(), moduleFactory.getListOfDetailViewTypes(activity.getResources()));
            } else {
                view.showToastAndFinishActivity("Discover Activity Not Recognized");
            }
        }
    }

    private void initInjection() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
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
