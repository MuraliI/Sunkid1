package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.mvp.view.TriptyHomechView;

public class TriptycHomePresenter implements BasePresenter {

    private TriptyHomechView view;

    public TriptycHomePresenter(TriptyHomechView view) {
        this.view = view;
        init();
    }

    private void init() {
        view.init();
    }
}
