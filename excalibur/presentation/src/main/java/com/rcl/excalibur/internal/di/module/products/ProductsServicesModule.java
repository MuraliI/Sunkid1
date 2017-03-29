package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.data.mapper.ProductEntityDataMapper;
import com.rcl.excalibur.data.mapper.ProductResponseDataMapper;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.data.service.DiscoverServicesImpl;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.domain.service.DiscoverServices;
import com.rcl.excalibur.internal.di.scopes.product.ProductsScope;

import dagger.Module;
import dagger.Provides;

@ProductsScope
@Module
public class ProductsServicesModule {

    @Provides
    protected BaseDataMapper<Product, ProductEntity> providesProductEntityDataMapper() {
        return new ProductEntityDataMapper();
    }

    @Provides
    protected ProductRepository providesProductRepository(BaseDataMapper<Product, ProductEntity> mapper) {
        return new ProductDataRepository(mapper);
    }

    @Provides
    protected ProductResponseDataMapper providesProductResponseDataMapper() {
        return new ProductResponseDataMapper();
    }

    @Provides
    protected DiscoverServices provideDiscoverService(ProductRepository productRepository,
                                                      ProductResponseDataMapper baseDataMapper) {
        return new DiscoverServicesImpl(productRepository, baseDataMapper);
    }

    @Provides
    protected GetProductsUseCase providesGetProductsUseCase(DiscoverServices discoverServices) {
        return new GetProductsUseCase(discoverServices);
    }
}
