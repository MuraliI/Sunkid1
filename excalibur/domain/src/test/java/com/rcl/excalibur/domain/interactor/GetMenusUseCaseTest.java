package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.service.MenuServices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.Mockito.verify;

public class GetMenusUseCaseTest {
    GetMenusUseCase getMenusUseCase;
    @Mock
    MenuServices menuServices;
    private final String VENUE_CODE ="GIOV";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getMenusUseCase = new GetMenusUseCase(menuServices,VENUE_CODE);
    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        DisposableObserver<List<Menu>> observer = new DisposableObserver<List<Menu>>() {
            @Override
            public void onNext(List<Menu> value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        getMenusUseCase.buildUseCaseObservable(observer, null);
        verify(menuServices).getMenu(observer,VENUE_CODE);
    }
}
