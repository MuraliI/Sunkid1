package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.SubCategoryResponseDataMapper;
import com.rcl.excalibur.data.service.response.GetSubCategoriesResponse;
import com.rcl.excalibur.data.service.response.SubCategoryResponse;
import com.rcl.excalibur.domain.SubCategory;
import com.rcl.excalibur.domain.service.SubCategoryServices;

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

public class SubCategoryServicesImpl extends BaseDataService<SubCategory, SubCategoryResponse, Void> implements SubCategoryServices {

//    private SubCategoryRepository subCategoryRepository;


    public SubCategoryServicesImpl() {
        super(new SubCategoryResponseDataMapper());
    }


//    private void mapSubCategories(Response<GetSubCategoriesResponse> response, List<SubCategory> subCategories) {
//        if (response.isSuccessful()) {
//            GetSubCategoriesResponse getGetSubCategoriesResponse = response.body();
//            if (isSuccess(getGetSubCategoriesResponse)) {
//                subCategories.addAll(subCategoryResponseDataMapper.transform(getGetSubCategoriesResponse.getCategory(), null));
//            }
//        }
//    }


    @Override
    public void getSubCategories(DisposableObserver<List<SubCategory>> observer, String sailingId) {
        Observable.create((ObservableOnSubscribe<List<SubCategory>>) observableEmitter -> {

            Call<GetSubCategoriesResponse> call = getDiscoverApi().getSubCategories(sailingId);

            call.enqueue(new Callback<GetSubCategoriesResponse>() {
                @Override
                public void onResponse(Call<GetSubCategoriesResponse> call, Response<GetSubCategoriesResponse> response) {
                    observableEmitter.onNext((response == null || !response.isSuccessful() || !isSuccess(response.body()))
                            ? null
                            : getMapper().transform(response.body().getCategory()));

                }

                @Override
                public void onFailure(Call<GetSubCategoriesResponse> call, Throwable t) {
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
