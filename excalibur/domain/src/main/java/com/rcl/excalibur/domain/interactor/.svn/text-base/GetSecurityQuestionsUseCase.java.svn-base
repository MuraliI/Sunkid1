package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.service.GuestServices;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class GetSecurityQuestionsUseCase extends UseCase<List<String>, Void> {
    private GuestServices guestServices;

    public GetSecurityQuestionsUseCase(GuestServices guestServices) {
        super();
        this.guestServices = guestServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<String>> observer, Void aVoid) {
        guestServices.getSecurityQuestions(observer);
    }
}
