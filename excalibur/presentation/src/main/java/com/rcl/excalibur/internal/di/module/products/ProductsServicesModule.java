package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.data.mapper.ProductEntityDataMapper;
import com.rcl.excalibur.data.mapper.ProductResponseDataMapper;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.data.service.ProductServiceImpl;
import com.rcl.excalibur.data.service.response.ProductResponse;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.domain.service.ProductService;
import com.rcl.excalibur.internal.di.scopes.product.ProductsScope;

import dagger.Module;
import dagger.Provides;

@ProductsScope
@Module
public class ProductsServicesModule {

    @Provides
    BaseDataMapper<Product, ProductEntity> providesProductEntityDataMapper() {
        return new ProductEntityDataMapper();
    }

    @Provides
    ProductRepository providesProductRepository(BaseDataMapper<Product, ProductEntity> mapper) {
        return new ProductDataRepository(mapper);
    }

    @Provides
    BaseDataMapper<Product, ProductResponse> providesProductResponseDataMapper() {
        return new ProductResponseDataMapper();
    }

    @Provides
    ProductService provideDiscoverService(ProductRepository productRepository,
                                          BaseDataMapper<Product, ProductResponse> baseDataMapper) {
        return new ProductServiceImpl(productRepository, baseDataMapper);
    }

    @Provides
    GetProductsUseCase providesGetProductsUseCase(ProductService productService) {
        return new GetProductsUseCase(productService);
    }
}
