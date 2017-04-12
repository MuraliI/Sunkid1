package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.mvp.view.TriptychHomeView;

public class TriptychHomePresenter {
    private TriptychHomeView view;

    public TriptychHomePresenter(TriptychHomeView view) {
        this.view = view;
    }

    public void init() {
        view.init();
    }

}
