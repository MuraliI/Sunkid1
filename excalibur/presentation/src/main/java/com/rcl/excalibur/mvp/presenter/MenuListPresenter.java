package com.rcl.excalibur.mvp.presenter;


import android.content.Context;
import android.content.DialogInterface;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.MenuItem;
import com.rcl.excalibur.model.MenuSectionModel;
import com.rcl.excalibur.mvp.view.MenuListView;
import com.rcl.excalibur.utils.AlertDialogUtils;

import java.util.List;

public class MenuListPresenter {

    private MenuListView view;
    private List<MenuItem> menuItems;

    public MenuListPresenter(MenuListView view) {
        this.view = view;
    }

    public void init(MenuSectionModel menuSectionModel) {
        final BaseActivity activity = view.getActivity();
        if (activity != null) {
            view.init();
            menuItems = menuSectionModel.getMenuSections();
            if (menuItems != null && menuItems.isEmpty()) {
                showMenuEmptyDialog();
            }
            view.addAll(menuItems);
        }
    }

    private void showMenuEmptyDialog() {
        Context context = view.getContext();
        if (view.getActivity() != null && context != null) {
            String message = context.getResources().getString(R.string.menu_empty_dialog_message);
            String buttonMessage = context.getResources().
                    getString(R.string.menu_empty_dialog_button);
            DialogInterface.OnClickListener listener = (dialog, which) -> view.getActivity().finish();
            AlertDialogUtils.showDialog(view.getActivity(), listener, message, buttonMessage);
        }
    }
}
