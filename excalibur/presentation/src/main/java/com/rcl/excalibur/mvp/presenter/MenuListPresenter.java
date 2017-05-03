package com.rcl.excalibur.mvp.presenter;

import android.content.res.Resources;

import com.rcl.excalibur.R;
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

    public void init(String menuName) {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        Resources resources = activity.getResources();
        view.init();
        menuItems = getMenuDbUseCase.getAllMenuItemByMenuName(menuName);
        if (menuItems == null) {
            view.showToastAndFinishActivity(resources.getString(R.string.no_menu_items_to_show));
            return;
        }
        view.addAll(menuItems);
    }
}
