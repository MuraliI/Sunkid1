package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.MenuItem;
import com.rcl.excalibur.domain.interactor.GetMenuDbUseCase;
import com.rcl.excalibur.mvp.view.MenuListView;

import java.util.List;

public class MenuListPresenter {

    private MenuListView view;
    private GetMenuDbUseCase getMenuDbUseCase;
    private List<MenuItem> menuItems;

    public MenuListPresenter(MenuListView view, GetMenuDbUseCase getMenuDbUseCase) {
        this.view = view;
        this.getMenuDbUseCase = getMenuDbUseCase;
    }

    public void init(String idTypeMenu) {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.init();
        menuItems = getMenuDbUseCase.getAllMenuItemByMenuName(idTypeMenu);
        if (menuItems == null) {
            view.showToastAndFinishActivity("Discover Item Not Found");
            return;
        }
        view.addAll(menuItems);
    }
}
