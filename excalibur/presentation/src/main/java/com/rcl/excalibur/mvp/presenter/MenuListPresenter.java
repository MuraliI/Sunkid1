package com.rcl.excalibur.mvp.presenter;


import android.content.DialogInterface;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.MenuItem;
import com.rcl.excalibur.domain.interactor.GetMenuDbUseCase;
import com.rcl.excalibur.mvp.view.MenuListView;
import com.rcl.excalibur.utils.AlertDialogUtils;

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
        view.init();
        menuItems = getMenuDbUseCase.getAllMenuItemByMenuName(menuName);
        if (menuItems != null && menuItems.isEmpty()) {
            showMenuEmptyDialog();
        }
        view.addAll(menuItems);
    }

    private void showMenuEmptyDialog() {
        if (view.getActivity() != null && view.getContext() != null) {
            String message = view.getContext().getResources().
                    getString(R.string.menu_empty_dialog_message);
            String buttonMessage = view.getContext().getResources().
                    getString(R.string.menu_empty_dialog_button);
            DialogInterface.OnClickListener listener = (dialog, which) -> view.getActivity().finish();
            AlertDialogUtils.showDialog(view.getActivity(), listener, message, buttonMessage);
        }
    }
}
