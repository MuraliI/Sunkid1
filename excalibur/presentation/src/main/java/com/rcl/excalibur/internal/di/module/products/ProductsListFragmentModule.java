package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.fragments.BaseFragment;
import com.rcl.excalibur.fragments.ProductsListFragment;
import com.rcl.excalibur.internal.di.scopes.product.ProductsListFragmentScope;
import com.rcl.excalibur.mvp.presenter.ProductsListPresenter;
import com.rcl.excalibur.mvp.view.ProductsListView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@ProductsListFragmentScope
@Module
public class ProductsListFragmentModule {
    private static final String PRODUCT_TYPE = "productType";
    private BaseFragment baseFragment;
    private int productType;

    public ProductsListFragmentModule(BaseFragment baseFragment, int productType) {
        this.baseFragment = baseFragment;
        this.productType = productType;
    }

    @Provides
    BaseFragment providesFragment() {
        return baseFragment;
    }

    @Provides
    @Named(PRODUCT_TYPE)
    int providesProductType() {
        return productType;
    }

    @Provides
    ProductsListView providesProductsListView(BaseFragment fragment) {
        return new ProductsListView(((ProductsListFragment) fragment));
    }

    @Provides
    ProductsListPresenter providesProductsListPresenter(@Named(PRODUCT_TYPE) int productType,
                                                        ProductsListView productsListView,
                                                        GetProductDbUseCase getProductDbUseCase) {
        return new ProductsListPresenter(productType, productsListView, getProductDbUseCase);
    }
}
