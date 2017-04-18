package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.SailDateInfoEvent;
import com.rcl.excalibur.domain.service.SailDateServices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.Mockito.verify;

public class GetSaildDateUseCaseTest {
    GetSaildDateUseCase getSaildDateUseCase;
    @Mock SailDateServices sailDateServices;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getSaildDateUseCase = new GetSaildDateUseCase(sailDateServices);
    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        DisposableObserver<SailDateInfoEvent> observer = new DisposableObserver<SailDateInfoEvent>() {
            @Override
            public void onNext(SailDateInfoEvent value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        getSaildDateUseCase.buildUseCaseObservable(observer, null);
        verify(sailDateServices).getSailDate();
    }

}