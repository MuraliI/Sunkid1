package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.mvp.view.TriptyHomechView;

import javax.inject.Inject;

public class TriptycHomePresenter implements BasePresenter {

    @Inject GetProductsUseCase getProductsUseCase;
    private TriptyHomechView view;

    public TriptycHomePresenter(TriptyHomechView view) {
        this.view = view;
        init();
        initInjection();
        getProductsUseCase.execute(null);
    }

    private void initInjection() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
    }


    private void init() {
        view.init();
    }
}
