package com.rcl.excalibur.mvp.view;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;


public class ActivityView<T extends AppCompatActivity> {
    protected Observer viewObserver;
    protected Observer adapterObserver;
    private WeakReference<T> activityRef;
    private CompositeDisposable compositeSubscription;


    public ActivityView(T activity) {
        activityRef = new WeakReference(activity);
    }

    @Nullable
    public T getActivity() {
        return activityRef.get();
    }

    @Nullable
    public Context getContext() {
        return getActivity();
    }


    @Nullable
    public FragmentManager getFragmentManager() {
        T activity = getActivity();
        return (activity != null) ? activity.getSupportFragmentManager() : null;
    }

    public void showMessage(@StringRes int message) {
        final T activity = getActivity();
        if (activity == null) {
            return;
        }
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String message) {
        final T activity = getActivity();
        if (activity == null) {
            return;
        }
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public void setViewObserver(Observer observer) {
        this.viewObserver = observer;
    }

    public void setAdapterObserver(Observer observer) {
        this.adapterObserver = observer;
    }


}
