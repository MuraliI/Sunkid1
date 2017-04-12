package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.service.GuestServices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class GetSecurityQuestionsUseCaseTest {
    GetSecurityQuestionsUseCase getSecurityQuestionsUseCase;
    @Mock GuestServices guestServices;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getSecurityQuestionsUseCase = new GetSecurityQuestionsUseCase(guestServices);

    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        DisposableObserver<List<String>> observer = new DisposableObserver<List<String>>() {
            @Override
            public void onNext(List<String> value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        getSecurityQuestionsUseCase.buildUseCaseObservable(observer, null);
        Mockito.verify(guestServices).getSecurityQuestions(observer);
    }

}