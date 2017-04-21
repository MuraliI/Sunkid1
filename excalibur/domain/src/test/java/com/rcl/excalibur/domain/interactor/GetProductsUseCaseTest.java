package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.service.DiscoverServices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.Mockito.verify;

public class GetProductsUseCaseTest {
    GetProductsUseCase getProductsUseCase;
    @Mock DiscoverServices discoverServices;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getProductsUseCase = new GetProductsUseCase(discoverServices);
    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        DisposableObserver<List<Product>> observer = new DisposableObserver<List<Product>>() {
            @Override
            public void onNext(List<Product> value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        // FIXME: Review this code
        /*getProductsUseCase.buildUseCaseObservable(observer, null);
        verify(discoverServices).getProducts();*/

    }

}