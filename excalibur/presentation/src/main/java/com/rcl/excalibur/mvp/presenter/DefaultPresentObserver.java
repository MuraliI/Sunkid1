package com.rcl.excalibur.mvp.presenter;


import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DefaultPresentObserver<T, P extends BasePresenter> implements Observer<T> {
    private WeakReference<P> weakReference;

    DefaultPresentObserver(P presenter) {
        this.weakReference = new WeakReference<>(presenter);
    }

    protected P getPresenter() {
        return weakReference.get();
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T value) {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
}
