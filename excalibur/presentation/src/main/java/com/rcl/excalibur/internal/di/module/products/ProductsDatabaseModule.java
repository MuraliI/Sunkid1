package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.internal.di.scopes.product.ProductsScope;

import dagger.Module;
import dagger.Provides;

@ProductsScope
@Module
public class ProductsDatabaseModule {

    @Provides
    protected GetProductDbUseCase providesGetProductDbUseCase(ProductRepository productRepository) {
        return new GetProductDbUseCase(productRepository);
    }
}
