package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.fragments.BaseFragment;
import com.rcl.excalibur.fragments.ProductsListFragment;
import com.rcl.excalibur.internal.di.scopes.product.ProductsListScope;
import com.rcl.excalibur.mvp.presenter.ProductsListPresenter;
import com.rcl.excalibur.mvp.view.ProductsListView;

import dagger.Module;
import dagger.Provides;

@ProductsListScope
@Module
public class ProductsListModule {
    private int productsType;
    private BaseFragment baseFragment;

    public ProductsListModule(int productsType, BaseFragment baseFragment) {
        this.productsType = productsType;
        this.baseFragment = baseFragment;
    }

    @Provides
    int provideProductsType() {
        return productsType;
    }

    @Provides
    BaseFragment providesFragment() {
        return baseFragment;
    }

    @Provides
    ProductsListView providesProductsListView(BaseFragment fragment) {
        return new ProductsListView(((ProductsListFragment) fragment));
    }

    @Provides
    ProductsListPresenter providesProductsListPresenter(int productType,
                                                        ProductsListView productsListView,
                                                        GetProductDbUseCase getProductDbUseCase) {
        return new ProductsListPresenter(productType, productsListView, getProductDbUseCase);
    }
}
