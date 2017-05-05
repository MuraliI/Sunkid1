package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.CategoryResponseDataMapper;
import com.rcl.excalibur.data.service.response.CategoryResponse;
import com.rcl.excalibur.data.service.response.GetCategoriesResponse;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.service.CategoryServices;

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

public class CategoryServicesImpl extends BaseDataService<Category, CategoryResponse, Void> implements CategoryServices {


    public CategoryServicesImpl() {
        super(new CategoryResponseDataMapper());
    }


    @Override
    public void getCategories(DisposableObserver<List<Category>> observer, String sailingId) {
        Observable.create((ObservableOnSubscribe<List<Category>>) observableEmitter -> {

            Call<GetCategoriesResponse> call = getDiscoverApi().getCategories(sailingId);

            call.enqueue(new Callback<GetCategoriesResponse>() {
                @Override
                public void onResponse(Call<GetCategoriesResponse> call, Response<GetCategoriesResponse> response) {
                    observableEmitter.onNext((response == null || !response.isSuccessful() || !isSuccess(response.body()))
                            ? null
                            : getMapper().transform(response.body().getCategory()));

                }

                @Override
                public void onFailure(Call<GetCategoriesResponse> call, Throwable t) {
                    observableEmitter.onNext(null);

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
