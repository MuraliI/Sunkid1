package com.rcl.excalibur.mvp.presenter;


import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DefaultPresentObserver<T, S extends BasePresenter> implements Observer<T> {
    protected WeakReference<S> weakReference;

    public DefaultPresentObserver(S presenter) {
        this.weakReference = new WeakReference(presenter);
    }

    protected S getPresenter() {
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
