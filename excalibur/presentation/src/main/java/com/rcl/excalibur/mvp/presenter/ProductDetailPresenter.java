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
import com.rcl.excalibur.mapper.ProductModelDataMapper;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.model.ProductModel;
import com.rcl.excalibur.mvp.view.ProductDetailView;

import java.util.List;

public class ProductDetailPresenter implements ActivityPresenter {
    private static final int MAX_BLUR_VALUE = 25;

    private GetProductDbUseCase getProductDbUseCase;
    private ProductDetailView view;
    private long productId;
    private Product product; //FIXME change model to correct one
    private List<RecyclerViewType> viewTypes;

    private boolean isToolbarCollapsed = false;
    private int scrollRange = -1;

    public ProductDetailPresenter(long productId, ProductDetailView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.productId = productId;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init() {
        product = getProductDbUseCase.get(productId); /* TODO map domain to a {@link ProductModel} */
        ProductModelDataMapper mapper = new ProductModelDataMapper();
        ProductModel productModel = mapper.transform(product);
        if (product != null) {
            if (!product.isReservationRequired() && product.isScheduable()) {
                view.showOnlyReservationIcon();
            }
            viewTypes = DetailViewTypeFactory.getAdaptersAndViewTypesForModel(productModel, view.getActivity().getResources());
            initView();
        } else {
            view.showToastAndFinishActivity("Discover Item Not Found");
        }
    }

    private void initView() {
        AppCompatActivity activity = view.getActivity();
        if (activity != null) {
            view.setupToolbar();
            view.setDetailTitle(product.getProductTitle());
            view.setHeroImage(BuildConfig.PREFIX_IMAGE + product.getHeroImageRefLink());
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
            activity.startActivity(ProductDeckMapActivity.getIntent(activity, productId));
        }
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
