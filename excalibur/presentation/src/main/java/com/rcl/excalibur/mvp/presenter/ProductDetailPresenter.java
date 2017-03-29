package com.rcl.excalibur.mvp.presenter;


import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactory;
import com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactoryProvider;
import com.rcl.excalibur.activity.ProductDeckMapActivity;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.ProductDetailView;

import static com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactoryProvider.TYPE_SHOREX;

public class ProductDetailPresenter implements ActivityPresenter {
    private GetProductDbUseCase getProductDbUseCase;
    private ProductDetailView view;
    private DetailModuleFactory moduleFactory;
    private long productId;
    private Product product;

    public ProductDetailPresenter(long productId, ProductDetailView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.productId = productId;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init() {
        product = getProductDbUseCase.get(productId);
        DetailModuleFactoryProvider factoryProvider = new DetailModuleFactoryProvider();
        if (product != null) {
            String productTypeName = product.getProductType().getProductType();
            if (!product.isReservationRequired() && product.isScheduable()) {
                view.showOnlyReservationIcon();
            }
            if (TYPE_SHOREX.equals(productTypeName)) {
                view.hideDeckMapButton();
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
            view.setHeroImage(BuildConfig.PREFIX_IMAGE + product.getProductMedia().getMediaItem().get(0).getMediaRefLink());
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

    public void onDeckMapClicked(long productId) {
        final BaseActivity activity = view.getActivity();
        if (activity != null) {
            activity.startActivity(ProductDeckMapActivity.getIntent(activity, productId));
        }
    }

    @Override
    public ProductDetailView getView() {
        return view;
    }

    private class DetailAdapterObserver extends DefaultPresentObserver<DiscoverItemModel, ProductDetailPresenter> {

        DetailAdapterObserver(ProductDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {
            //TODO do something when a detail item is clicked.
        }
    }
}
