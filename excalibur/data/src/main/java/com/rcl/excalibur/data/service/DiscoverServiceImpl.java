package com.rcl.excalibur.data.service;


import android.util.Log;

import com.rcl.excalibur.data.mapper.ProductResponseDataMapper;
import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.EntertaimentsResponse;
import com.rcl.excalibur.data.service.response.GetProductsResponse;
import com.rcl.excalibur.data.service.response.ProductsResponse;
import com.rcl.excalibur.data.utils.ServiceUtil;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.domain.service.DiscoverService;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class DiscoverServiceImpl implements DiscoverService {
    private final ProductRepository productRepository;
    private final ProductResponseDataMapper productResponseDataMapper;

    @Inject
    public DiscoverServiceImpl(ProductRepository productRepository, ProductResponseDataMapper productResponseDataMapper) {
        this.productRepository = productRepository;
        this.productResponseDataMapper = productResponseDataMapper;
    }

    @Override
    public void getCategories() {

        Call<CategoriesResponse> call = ServiceUtil.getDiscoverApi().getCategories();

        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                Log.d("Succesfull", response.body().getGetCategoriesResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                //Handle failure
                Log.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getEntertaiments() {

        Call<EntertaimentsResponse> call = ServiceUtil.getDiscoverApi().getEntertaiments();

        call.enqueue(new Callback<EntertaimentsResponse>() {
            @Override
            public void onResponse(Call<EntertaimentsResponse> call, Response<EntertaimentsResponse> response) {
                Log.d("Succesfull", response.body().getGetProductsResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<EntertaimentsResponse> call, Throwable t) {
                //Handle failure
                Log.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getProducts() {

        Call<ProductsResponse> call = ServiceUtil.getDiscoverApi().getProducts();

        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful()) {
                    GetProductsResponse getProductsResponse = response.body().getGetProductsResponse();
                    if (ServiceUtil.isSuccess(getProductsResponse)) {
                        productRepository.create(productResponseDataMapper.transform(getProductsResponse.getProducts()));
                        return;
                    }
                }

            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                //Handle failure
                Log.e("error", t.getMessage());
            }
        });
    }


}
