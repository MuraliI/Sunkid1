package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.internal.di.component.FragmentComponent;
import com.rcl.excalibur.internal.di.module.FragmentModule;
import com.rcl.excalibur.mvp.presenter.FragmentPresenter;

import javax.inject.Inject;

public abstract class BaseFragment<P extends FragmentPresenter> extends Fragment {
    @Inject
    protected P presenter;
    protected RCLApp application;
    private FragmentComponent fragmentComponent;

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        application = (RCLApp) getActivity().getApplication();
        createFragmentComponent();
        return view;
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inject(fragmentComponent);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyFragmentComponent();
    }

    protected void createFragmentComponent() {
        fragmentComponent = ((RCLApp) getActivity().getApplication()).getAppComponent().plus(new FragmentModule(this));
    }

    protected void destroyFragmentComponent() {
        fragmentComponent = null;
    }

    protected abstract void inject(FragmentComponent fragmentComponent);
}
