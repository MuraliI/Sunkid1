package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.mvp.view.TriptychHomeView;

public class TriptychHomePresenter implements ActivityPresenter {
    private TriptychHomeView view;

    public TriptychHomePresenter(TriptychHomeView view) {
        this.view = view;
    }

    public void init() {
        view.init();
    }

    @Override
    public TriptychHomeView getView() {
        return view;
    }
}
