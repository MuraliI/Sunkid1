package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.SubCategory;
import com.rcl.excalibur.domain.service.DiscoverServices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.Mockito.verify;


public class GetSubCategoriesUseCaseTest {

    GetSubCategoriesUseCase getSubCategoriesUseCase;
    @Mock DiscoverServices discoverServices;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getSubCategoriesUseCase = new GetSubCategoriesUseCase(discoverServices);
    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        DisposableObserver<List<SubCategory>> observer = new DisposableObserver<List<SubCategory>>() {
            @Override
            public void onNext(List<SubCategory> value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        getSubCategoriesUseCase.buildUseCaseObservable(observer, null);
        verify(discoverServices).getSubCategories();

    }

}