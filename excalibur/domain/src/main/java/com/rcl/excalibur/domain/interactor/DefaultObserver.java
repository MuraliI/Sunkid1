package com.rcl.excalibur.domain.interactor;

import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Default {@link DisposableObserver} base class to be used whenever you want default error handling.
 */
public abstract class DefaultObserver<T> extends DisposableObserver<T> {

    @Override
    public void onComplete() {
        // Optional
    }

    @Override
    public void onError(Throwable exception) {
        Timber.e(exception.getMessage(), exception);
    }
}
