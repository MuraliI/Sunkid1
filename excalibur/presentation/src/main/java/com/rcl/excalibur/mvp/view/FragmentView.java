package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.rcl.excalibur.activity.BaseActivity;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;


public class FragmentView<T extends Fragment> {
    protected Observer viewObserver;
    protected Observer adapterObserver;
    private WeakReference<T> fragmentRef;

    FragmentView(T fragment) {
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

    public void setViewObserver(Observer observer) {
        this.viewObserver = observer;
    }

    public void setAdapterObserver(Observer observer) {
        this.adapterObserver = observer;
    }
}
