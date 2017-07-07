package com.rcl.excalibur.mvp.view.base;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.rcl.excalibur.activity.BaseActivity;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;


public class FragmentView<T extends Fragment, HV, HA> {
    protected Observer<HV> viewObserver;
    protected Observer<HA> adapterObserver;
    private WeakReference<T> fragmentRef;

    protected FragmentView(T fragment) {
        fragmentRef = new WeakReference<>(fragment);
    }

    @Nullable
    public T getFragment() {
        return fragmentRef.get();
    }

    @Nullable
    public BaseActivity getActivity() {
        return (BaseActivity) fragmentRef.get().getActivity();
    }

    @Nullable
    public Context getContext() {
        return getActivity();
    }

    public void showMessage(@StringRes int message) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String message) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public Observer<HV> getViewObserver() {
        return viewObserver;
    }

    public void setViewObserver(Observer<HV> observer) {
        this.viewObserver = observer;
    }

    public Observer<HA> getAdapterObserver() {
        return adapterObserver;
    }

    public void setAdapterObserver(Observer<HA> observer) {
        this.adapterObserver = observer;
    }

    public void hideKeyboard() {
        final BaseActivity activity = getActivity();
        if (activity == null || activity.getCurrentFocus() == null)
            return;
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void onViewNext(HV value) {
        if (viewObserver == null) {
            return;
        }
        viewObserver.onNext(value);
    }
}
