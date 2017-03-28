package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.service.DiscoverServices;

import static org.mockito.Mockito.mock;

public class ProductsServicesModuleTest extends ProductsServicesModule {

    @Override
    protected GetProductsUseCase providesGetProductsUseCase(DiscoverServices discoverServices) {
        return mock(GetProductsUseCase.class);
    }
}
