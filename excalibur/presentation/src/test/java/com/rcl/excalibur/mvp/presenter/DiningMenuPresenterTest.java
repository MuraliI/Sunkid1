package com.rcl.excalibur.mvp.presenter;

import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DiningMenuActivity;
import com.rcl.excalibur.domain.interactor.GetMenuDbUseCase;
import com.rcl.excalibur.domain.interactor.GetMenusUseCase;
import com.rcl.excalibur.mvp.view.DiningMenuView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class DiningMenuPresenterTest {

    DiningMenuPresenter presenter;
    @Mock DiningMenuView view;
    @Mock GetMenusUseCase getMenusUseCase;
    @Mock GetMenuDbUseCase getMenuDbUseCase;
    @Mock DiningMenuActivity baseActivity;
    @Mock ViewPager viewPager;

    List<String> menuNames;
    private final String MENU_NAME_DINNER = "Dinner";
    private final String MENU_NAME_BREAKFAST = "Breakfast";
    private final String MENU_NAME_LUNCH = "Lunch";

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(TextUtils.class);
        MockitoAnnotations.initMocks(this);
        presenter = new DiningMenuPresenter(view, getMenuDbUseCase, getMenusUseCase);
        menuNames = new ArrayList<>();
        menuNames.add(MENU_NAME_BREAKFAST);
        menuNames.add(MENU_NAME_LUNCH);
        menuNames.add(MENU_NAME_DINNER);
        when(view.getActivity()).thenReturn(baseActivity);
        when(getMenuDbUseCase.getAllMenuName()).thenReturn(menuNames);
    }

    @After
    public void tearDown() throws Exception {
        menuNames = null;
    }

    @Test
    public void init() throws Exception {
        presenter.createFragmentMenu(viewPager);
        //verify(view).getActivity();
        verify(getMenuDbUseCase).getAllMenuName();
    }

}