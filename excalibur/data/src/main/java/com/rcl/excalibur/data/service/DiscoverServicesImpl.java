package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.ProductResponseDataMapper;
import com.rcl.excalibur.data.service.response.ActivitiesResponse;
import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.DiningsResponse;
import com.rcl.excalibur.data.service.response.EntertainmentsResponse;
import com.rcl.excalibur.data.service.response.ExcursionResponse;
import com.rcl.excalibur.data.service.response.GetProductsResponse;
import com.rcl.excalibur.data.service.response.ProductsResponse;
import com.rcl.excalibur.data.service.response.PromotionMessagesResponse;
import com.rcl.excalibur.data.service.response.SpasResponse;
import com.rcl.excalibur.data.utils.ServiceUtil;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.domain.service.DiscoverServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class DiscoverServicesImpl implements DiscoverServices {
    private final ProductRepository productRepository;
    private final ProductResponseDataMapper productResponseDataMapper;

    public DiscoverServicesImpl(ProductRepository productRepository, ProductResponseDataMapper productResponseDataMapper) {
        this.productRepository = productRepository;
        this.productResponseDataMapper = productResponseDataMapper;
    }

    @Override
    public void getCategories() {

        Call<CategoriesResponse> call = ServiceUtil.getDiscoverApi().getCategories();

        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                Timber.d("Succesfull", response.body().getGetCategoriesResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                //Handle failure
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getSpas() {
        Call<SpasResponse> call = ServiceUtil.getDiscoverApi().getSpas();

        call.enqueue(new Callback<SpasResponse>() {
            @Override
            public void onResponse(Call<SpasResponse> call, Response<SpasResponse> response) {
                Timber.d("Succesfull", response.body().getGetSpasResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<SpasResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getExcursion() {

        Call<ExcursionResponse> call = ServiceUtil.getDiscoverApi().getExcursion();

        call.enqueue(new Callback<ExcursionResponse>() {
            @Override
            public void onResponse(Call<ExcursionResponse> call, Response<ExcursionResponse> response) {
                Timber.d("Succesfull", response.body().getGetExcursionsResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<ExcursionResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getDinings() {
        Call<DiningsResponse> call = ServiceUtil.getDiscoverApi().getDinings();

        call.enqueue(new Callback<DiningsResponse>() {
            @Override
            public void onResponse(Call<DiningsResponse> call, Response<DiningsResponse> response) {
                Timber.d("Succesfull", response.body().getGetDiningsResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<DiningsResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getActivities() {

        Call<ActivitiesResponse> call = ServiceUtil.getDiscoverApi().getActivities();
        call.enqueue(new Callback<ActivitiesResponse>() {
            @Override
            public void onResponse(Call<ActivitiesResponse> call, Response<ActivitiesResponse> response) {
                Timber.d("Succesfull", response.body().getGetActivitiesResponse().getResponseStatus());

            }

            @Override
            public void onFailure(Call<ActivitiesResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getPromotionMessages() {
        Call<PromotionMessagesResponse> call = ServiceUtil.getDiscoverApi().getPromotionMessages();
        call.enqueue(new Callback<PromotionMessagesResponse>() {
            @Override
            public void onResponse(Call<PromotionMessagesResponse> call, Response<PromotionMessagesResponse> response) {
                Timber.d("Succesfull", response.body().getGetPromotionMessages().getResponseStatus());

            }

            @Override
            public void onFailure(Call<PromotionMessagesResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void getEntertainments() {

        Call<EntertainmentsResponse> call = ServiceUtil.getDiscoverApi().getEntertainments();

        call.enqueue(new Callback<EntertainmentsResponse>() {
            @Override
            public void onResponse(Call<EntertainmentsResponse> call, Response<EntertainmentsResponse> response) {
                Timber.d("Succesfull", response.body().getGetEntertainmentsResponse().getResponseStatus());
            }

            @Override
            public void onFailure(Call<EntertainmentsResponse> call, Throwable t) {
                //Handle failure
                Timber.e("error", t.getMessage());
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
                Timber.e("error", t.getMessage());
            }
        });
    }


}
