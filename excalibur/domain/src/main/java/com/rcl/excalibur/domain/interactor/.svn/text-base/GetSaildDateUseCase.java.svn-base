package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.service.SailDateServices;

import io.reactivex.observers.DisposableObserver;

public class GetSaildDateUseCase extends UseCase<SailDateInfo, Void> {

    private final SailDateServices sailDateServices;

    public GetSaildDateUseCase(SailDateServices sailDateServices) {
        super();
        this.sailDateServices = sailDateServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<SailDateInfo> observer, Void aVoid) {
        sailDateServices.getSailDate();

    }
}
