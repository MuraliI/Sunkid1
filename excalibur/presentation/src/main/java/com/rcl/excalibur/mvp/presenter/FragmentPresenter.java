package com.rcl.excalibur.mvp.presenter;

import android.support.annotation.VisibleForTesting;

import com.rcl.excalibur.mvp.view.FragmentView;

public interface FragmentPresenter extends BasePresenter {
    @VisibleForTesting
    FragmentView getView();
}
