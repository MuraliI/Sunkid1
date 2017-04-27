package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.service.DiscoverServices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.Mockito.verify;

public class GetMenuesUseCaseTest {
    GetMenuesUseCase getMenuesUseCase;
    @Mock
    DiscoverServices discoverServices;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getMenuesUseCase = new GetMenuesUseCase(discoverServices);
    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        DisposableObserver<List<Menu>> observer = new DisposableObserver<List<Menu>>() {
            @Override
            public void onNext(List<Menu> value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        getMenuesUseCase.buildUseCaseObservable(observer, null);
        verify(discoverServices).getMenues();
    }
}
