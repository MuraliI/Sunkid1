package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.view.DiscoverTabView;

/**
 * Created by asielgil on 3/17/17.
 */

public class DiscoverTabPresenter implements BasePresenter {

    private DiscoverTabView view;

    public DiscoverTabPresenter(DiscoverTabView view) {
        this.view = view;
        initInjection();
        init();
    }

    private void init() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
    }

    private void initInjection() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
    }

}

