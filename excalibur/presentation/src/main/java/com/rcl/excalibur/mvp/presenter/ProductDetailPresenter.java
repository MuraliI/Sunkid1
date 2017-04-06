package com.rcl.excalibur.mvp.presenter;


import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDeckMapActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.factory.DetailViewTypeFactory;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.view.ProductDetailView;

import java.util.List;

public class ProductDetailPresenter implements ActivityPresenter {
    private GetProductDbUseCase getProductDbUseCase;
    private ProductDetailView view;
    private long productId;
    private Product product;
    private List<RecyclerViewType> viewTypes;

    public ProductDetailPresenter(long productId, ProductDetailView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.productId = productId;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init() {
        product = getProductDbUseCase.get(productId);
        if (product != null && view.getActivity() != null) {
            viewTypes = DetailViewTypeFactory.getAdaptersAndViewTypesForModel(product, view.getActivity().getResources());
            initView();
        } else {
            view.showToastAndFinishActivity("Discover Item Not Found");
        }
    }

    private void initView() {
        AppCompatActivity activity = view.getActivity();
        if (activity != null) {
            if (product.getProductMedia() != null
                    && product.getProductMedia().getMediaItem() != null
                    && product.getProductMedia().getMediaItem().size() > 0) {
                view.setHeroImage(BuildConfig.PREFIX_IMAGE + product.getProductMedia().getMediaItem().get(0));
            } else {
                view.setHeroImage(null);
            }
            view.setAdapterObserver(new FindOnDeckClickObserver(this));
            view.render(viewTypes);
        }
    }

    public void onBackClicked() {
        if (view.getActivity() != null) {
            view.getActivity().finish();
        }
    }

    @Override
    public ProductDetailView getView() {
        return view;
    }

    private class FindOnDeckClickObserver extends DefaultPresentObserver<Long, ProductDetailPresenter> {

        FindOnDeckClickObserver(ProductDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(Long value) {
            final BaseActivity activity = view.getActivity();
            if (activity != null) {
                activity.startActivity(ProductDeckMapActivity.getIntent(activity, value));
            }
        }
    }
}
