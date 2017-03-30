package com.rcl.excalibur.mvp.presenter;


import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.factory.DetailViewTypeFactory;
import com.rcl.excalibur.deckmap.activity.DiscoverDeckMapActivity;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.model.ProductModel;
import com.rcl.excalibur.mvp.view.ProductDetailView;

import java.util.List;

public class ProductDetailPresenter implements ActivityPresenter {
    private GetProductDbUseCase getProductDbUseCase;
    private ProductDetailView view;
    private long productId;
    private Product product; //FIXME change model to correct one
    private List<RecyclerViewType> viewTypes;

    public ProductDetailPresenter(long productId, ProductDetailView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.productId = productId;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init() {
        product = getProductDbUseCase.get(productId); /* TODO map domain to a {@link ProductModel} */
        ProductModel productModel = new ProductModel(); //FIXME get the actual map
        if (product != null) {
            if (!product.isReservationRequired() && product.isScheduable()) {
                view.showOnlyReservationIcon();
            }
            viewTypes = DetailViewTypeFactory.getAdaptersAndViewTypesForModel(productModel);
            initView();
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
            view.render(viewTypes);
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
            activity.startActivity(DiscoverDeckMapActivity.getIntent(activity, productId));
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
