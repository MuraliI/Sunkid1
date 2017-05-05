package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.service.MenuServices;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


public class GetMenusUseCase extends UseCase<List<Menu>, Void> {

    private final MenuServices menuServices;
    private final String venueCode;

    public GetMenusUseCase(MenuServices menuServices, String venueCode) {
        super();
        this.menuServices = menuServices;
        this.venueCode = venueCode;
    }



    @Override
    void buildUseCaseObservable(DisposableObserver<List<Menu>> observer, Void aVoid) {
        menuServices.getMenu(observer, venueCode);

    }
}
