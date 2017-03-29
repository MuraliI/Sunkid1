package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.domain.repository.ProductRepository;

import static org.mockito.Mockito.mock;

public class ProductsDatabaseModuleTest extends ProductsDatabaseModule {

    @Override
    protected GetProductDbUseCase providesGetProductDbUseCase(ProductRepository productRepository) {
        return mock(GetProductDbUseCase.class);
    }
}
