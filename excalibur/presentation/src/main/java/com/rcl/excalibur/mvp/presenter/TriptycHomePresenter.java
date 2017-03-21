package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.mvp.view.TriptychView;

public class TriptycHomePresenter implements BasePresenter {

    private TriptychView view;

    public TriptycHomePresenter(TriptychView view) {
        this.view = view;
        init();
    }

    private void init() {
        view.init();
    }
}
