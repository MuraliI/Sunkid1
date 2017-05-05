package com.rcl.excalibur.data.service;


import android.util.Log;

import com.rcl.excalibur.data.mapper.CategoryResponseDataMapper;
import com.rcl.excalibur.data.mapper.OfferingResponseMapper;
import com.rcl.excalibur.data.mapper.PriceResponseMapper;
import com.rcl.excalibur.data.mapper.ProductResponseDataMapper;
import com.rcl.excalibur.data.service.response.ActivitiesResponse;
import com.rcl.excalibur.data.service.response.CategoriesResponse;
import com.rcl.excalibur.data.service.response.DiningsResponse;
import com.rcl.excalibur.data.service.response.EntertainmentsResponse;
import com.rcl.excalibur.data.service.response.ExcursionResponse;
import com.rcl.excalibur.data.service.response.GetCategoriesResponse;
import com.rcl.excalibur.data.service.response.GetProductsResponse;
import com.rcl.excalibur.data.service.response.ProductResponse;
import com.rcl.excalibur.data.service.response.PromotionMessagesResponse;
import com.rcl.excalibur.data.service.response.SpasResponse;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.CategoryRepository;
import com.rcl.excalibur.domain.service.DiscoverServices;
import com.rcl.excalibur.domain.utils.ProductsInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getDiscoverApi;
import static com.rcl.excalibur.data.utils.ServiceUtil.isSuccess;

public class DiscoverServicesImpl extends BaseDataService<Product, ProductResponse, Void> implements DiscoverServices {
    public static final String SAILING_ID = "AL20170702";
    private static final int MAX_COUNT = 50;

    private CategoryRepository categoryRepository;
    private CategoryResponseDataMapper categoryResponseDataMapper;


    public DiscoverServicesImpl() {
        super(new ProductResponseDataMapper(new OfferingResponseMapper(new PriceResponseMapper())));
    }


    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void setCategoryResponseDataMapper(CategoryResponseDataMapper categoryResponseDataMapper) {
        this.categoryResponseDataMapper = categoryResponseDataMapper;
    }

    @Override
    public void getSubCategories() {
        List<Category> categories = new ArrayList<>();


        Call<GetCategoriesResponse> categoriesCall = getDiscoverApi().getCategories(SAILING_ID);

        categoriesCall.enqueue(new Callback<GetCategoriesResponse>() {

            @Override
            public void onResponse(Call<GetCategoriesResponse> call, Response<GetCategoriesResponse> response) {
                //FIXME implement logic in scheduler to avoid delete & duplicates
                mapCategories(response, categories);
                categoryRepository.create(categories);
            }

            @Override
            public void onFailure(Call<GetCategoriesResponse> call, Throwable t) {
                logOnFailureError(t, "");
            }
        });
    }

    private void mapCategories(Response<GetCategoriesResponse> response, List<Category> categories) {
        if (response.isSuccessful()) {
            GetCategoriesResponse getGetCategoriesResponse = response.body();
            if (isSuccess(getGetCategoriesResponse)) {
                categories.addAll(categoryResponseDataMapper.transform(getGetCategoriesResponse.getCategory(), null));
            }
        }
    }

    @Override
    public void getCategories() {

        Call<CategoriesResponse> call = getDiscoverApi().getCategories();

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
        Call<SpasResponse> call = getDiscoverApi().getSpas();

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

        Call<ExcursionResponse> call = getDiscoverApi().getExcursion();

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
        Call<DiningsResponse> call = getDiscoverApi().getDinings();

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

        Call<ActivitiesResponse> call = getDiscoverApi().getActivities();
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
        Call<PromotionMessagesResponse> call = getDiscoverApi().getPromotionMessages();
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

        Call<EntertainmentsResponse> call = getDiscoverApi().getEntertainments();

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
    public void getProducts(DisposableObserver<ProductsInfo> observer, String sailingId, String type, int maxCount
            , int offset) {
        Observable.create((ObservableOnSubscribe<ProductsInfo>) observableEmitter -> {

            Call<GetProductsResponse> call = getDiscoverApi().getProducts(sailingId, type, maxCount, offset);

            call.enqueue(new Callback<GetProductsResponse>() {
                @Override
                public void onResponse(Call<GetProductsResponse> call, Response<GetProductsResponse> response) {
                    if (response == null || !response.isSuccessful() || !isSuccess(response.body())) {
                        Log.d("DownloadProductsManager", "DownloadProductsManageraaremove CERO : " + type + "  " + 0);
                        observableEmitter.onNext(new ProductsInfo(type));
                        return;
                    }
                    Log.d("DownloadProductsManager", "DownloadProductsManageraaremove OK : " + type + "  " + response.body()
                            .getProducts().size());
                    List<Product> products = getMapper().transform(response.body().getProducts());
                    observableEmitter.onNext(new ProductsInfo(type, response.body().getSummaryResponse().getTotalHits(), products));

                }

                @Override
                public void onFailure(Call<GetProductsResponse> call, Throwable t) {
                    Log.d("DownloadProductsManager", "DownloadProductsManageraaremove FAILED: " + type + "  " + t.getMessage());
                    observableEmitter.onNext(new ProductsInfo(type));

                }
            });

        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    private void logOnFailureError(Throwable t, String category) {
        Timber.e(t, "Error on %s call, message = %s", category, t.getMessage());
    }

}
