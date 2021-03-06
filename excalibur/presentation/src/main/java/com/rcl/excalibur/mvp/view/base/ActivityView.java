package com.rcl.excalibur.mvp.view.base;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.rcl.excalibur.activity.BaseActivity;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;

public class ActivityView<T extends BaseActivity, HV, HA> {
    protected Observer<HV> viewObserver;
    protected Observer<HA> adapterObserver;
    private WeakReference<T> activityRef;

    public ActivityView(T activity) {
        activityRef = new WeakReference<>(activity);
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

    public Observer<HA> getAdapterObserver() {
        return this.adapterObserver;
    }

    public void setAdapterObserver(Observer<HA> observer) {
        this.adapterObserver = observer;
    }

    public Observer<HV> getViewObserver() {
        return this.viewObserver;
    }

    public void setViewObserver(Observer<HV> observer) {
        this.viewObserver = observer;
    }

    public void hideKeyboard() {
        final T activity = getActivity();
        if (activity == null || activity.getCurrentFocus() == null)
            return;
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
