package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.data.mapper.ProductEntityDataMapper;
import com.rcl.excalibur.data.mapper.ProductResponseDataMapper;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.data.service.DiscoverServicesImpl;
import com.rcl.excalibur.data.service.api.DiscoverApi;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.domain.service.DiscoverServices;
import com.rcl.excalibur.internal.di.scopes.product.ProductsScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@ProductsScope
@Module
public class ProductsServicesModule {

    @Provides
    protected ProductEntityDataMapper providesProductEntityDataMapper() {
        return new ProductEntityDataMapper();
    }

    @Provides
    protected ProductRepository providesProductRepository(ProductEntityDataMapper mapper) {
        return new ProductDataRepository(mapper);
    }

    @Provides
    protected ProductResponseDataMapper providesProductResponseDataMapper() {
        return new ProductResponseDataMapper();
    }

    @Provides
    protected DiscoverApi providesDiscoverApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BuildConfig.DISCOVER_API_URL).
                addConverterFactory(GsonConverterFactory.create()).
                client(okHttpClient)
                .build();
        return retrofit.create(DiscoverApi.class);
    }

    @Provides
    protected DiscoverServices providesDiscoverService(ProductRepository productRepository,
                                                       ProductResponseDataMapper baseDataMapper,
                                                       DiscoverApi discoverApi) {
        return new DiscoverServicesImpl(productRepository, baseDataMapper, discoverApi);
    }

    @Provides
    protected GetProductsUseCase providesGetProductsUseCase(DiscoverServices discoverServices) {
        return new GetProductsUseCase(discoverServices);
    }
}
