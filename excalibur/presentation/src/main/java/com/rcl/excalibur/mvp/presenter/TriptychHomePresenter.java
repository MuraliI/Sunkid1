package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateUseCase;
import com.rcl.excalibur.domain.interactor.GetSubCategoriesUseCase;
import com.rcl.excalibur.mvp.view.TriptychHomeView;

public class TriptychHomePresenter {
    private TriptychHomeView view;

    private GetProductsUseCase getProductsUseCase;
    private GetSubCategoriesUseCase getSubCategoriesUseCase;
    private GetSaildDateUseCase getSaildDateUseCase;

    public TriptychHomePresenter(TriptychHomeView view, GetProductsUseCase getProductsUseCase,
                                 GetSubCategoriesUseCase getSubCategoriesUseCase, GetSaildDateUseCase getSaildDateUseCase) {
        this.view = view;
        this.getProductsUseCase = getProductsUseCase;
        this.getSubCategoriesUseCase = getSubCategoriesUseCase;
        this.getSaildDateUseCase = getSaildDateUseCase;
    }

    public void init() {
        view.init();

        getProductsUseCase.execute(new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                view.onServiceCallCompleted(value);
            }
        }, null);

        getSubCategoriesUseCase.execute(null);
        getSaildDateUseCase.execute(null);
    }

}
