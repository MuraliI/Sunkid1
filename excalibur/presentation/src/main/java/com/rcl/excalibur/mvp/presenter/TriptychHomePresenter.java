package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.mvp.view.TriptychHomeView;

public class TriptychHomePresenter {
    private TriptychHomeView view;
    private GetProductsUseCase getProductsUseCase;
    public TriptychHomePresenter(TriptychHomeView view, GetProductsUseCase getProductsUseCase) {
        this.view = view;
        this.getProductsUseCase = getProductsUseCase;
    }

    public void init() {
        view.init();
        getProductsUseCase.execute(new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                view.onServiceCallCompleted(value);
            }
        }, null);
    }

}
