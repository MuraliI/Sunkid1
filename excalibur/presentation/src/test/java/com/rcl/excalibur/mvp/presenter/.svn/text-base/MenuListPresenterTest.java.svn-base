package com.rcl.excalibur.mvp.presenter;

import android.content.res.Resources;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.MenuItem;
import com.rcl.excalibur.domain.interactor.GetMenuDbUseCase;
import com.rcl.excalibur.mvp.view.MenuListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MenuListPresenterTest {

    MenuListPresenter presenter;
    @Mock MenuListView view;
    @Mock GetMenuDbUseCase getMenuDbUseCase;
    @Mock BaseActivity activity;
    @Mock Resources resources;

    private List<MenuItem> menuItemList;
    private MenuItem menuItem;

    private final String MENU_NAME = "Dinner";
    private final String MENU_ITEM_NAME = "WILD MUSHROOM SOUP";
    private final String MENU_ITEM_DESCRIPTION = "Scented with white truffle oil, chives";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        menuItemList = new ArrayList<>();
        menuItem = new MenuItem();
        menuItem.setMenuItemTitle(MENU_ITEM_NAME);
        menuItem.setMenuItemDescription(MENU_ITEM_DESCRIPTION);
        menuItemList.add(menuItem);
        presenter = new MenuListPresenter(view, getMenuDbUseCase);
        when(view.getActivity()).thenReturn(activity);
        when(getMenuDbUseCase.getAllMenuItemByMenuName(MENU_NAME)).thenReturn(menuItemList);
        when(view.getActivity().getResources()).thenReturn(resources);
    }

    @Test
    public void init() throws Exception {
        verify(view).getActivity();
        presenter.init(MENU_NAME);
        verify(getMenuDbUseCase).getAllMenuItemByMenuName(MENU_NAME);
    }
}