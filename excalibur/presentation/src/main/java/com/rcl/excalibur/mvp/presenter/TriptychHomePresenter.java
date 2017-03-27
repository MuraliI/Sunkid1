package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.mvp.view.TriptychHomeView;

public class TriptychHomePresenter implements ProductsActivityPresenter {
    private GetProductsUseCase getProductsUseCase;
    private TriptychHomeView view;

    public TriptychHomePresenter(TriptychHomeView view, GetProductsUseCase getProductsUseCase) {
        this.view = view;
        this.getProductsUseCase = getProductsUseCase;
    }

    public void start() {
        this.view.init();
        this.getProductsUseCase.execute(null);
    }

    @Override
    public TriptychHomeView getView() {
        return view;
    }
}
