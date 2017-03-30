package com.rcl.excalibur.mvp.presenter;

import android.support.annotation.VisibleForTesting;

import com.rcl.excalibur.mvp.view.base.ActivityView;

public interface ActivityPresenter extends BasePresenter {
    @VisibleForTesting
    ActivityView getView();
}
