package com.rcl.excalibur.mvp.presenter;


import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDeckMapActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.factory.DetailViewTypeFactory;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.view.ProductDetailView;

import java.util.List;

public class ProductDetailPresenter implements ActivityPresenter {
    private static final int MAX_BLUR_VALUE = 25;

    private GetProductDbUseCase getProductDbUseCase;
    private ProductDetailView view;
    private long productId;
    private Product product;
    private List<RecyclerViewType> viewTypes;

    private boolean isToolbarCollapsed = false;
    private int scrollRange = -1;

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
            view.setupToolbar();
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

    public void onOffsetChanged(int verticalOffset, int totalScrollRange) {
        view.setBlurRadiusOnImage((Math.abs(verticalOffset) * MAX_BLUR_VALUE) / totalScrollRange);

        if (scrollRange == -1) {
            scrollRange = totalScrollRange;
        }

        if (scrollRange + verticalOffset == 0) {
            view.setContentScrimResource(R.color.colorPrimary);
            isToolbarCollapsed = true;
        } else if (isToolbarCollapsed) {
            view.setContentScrimResource(R.color.transparent);
            isToolbarCollapsed = false;
        }
    }

    private class FindOnDeckClickObserver extends DefaultPresentObserver<String, ProductDetailPresenter> {

        FindOnDeckClickObserver(ProductDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(String value) {
            final BaseActivity activity = view.getActivity();
            if (activity != null) {
                activity.startActivity(ProductDeckMapActivity.getIntent(activity, productId));
            }
        }
    }
}
