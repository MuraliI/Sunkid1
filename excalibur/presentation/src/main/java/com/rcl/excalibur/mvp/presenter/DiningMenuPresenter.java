package com.rcl.excalibur.mvp.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.ViewPager;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.DiningMenuPagerAdapter;
import com.rcl.excalibur.domain.interactor.GetMenuDbUseCase;
import com.rcl.excalibur.fragments.MenuListFragment;
import com.rcl.excalibur.model.MenuSectionModel;
import com.rcl.excalibur.mvp.view.DiningMenuView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.AlertDialogUtils;

import java.util.ArrayList;
import java.util.List;

public class DiningMenuPresenter {

    private final DiningMenuView view;
    private final GetMenuDbUseCase getMenuDbUseCase;
    private static final int MINIMUM_SECTIONS = 0;

    public DiningMenuPresenter(DiningMenuView view, GetMenuDbUseCase getMenuDbUseCase) {
        this.view = view;
        this.getMenuDbUseCase = getMenuDbUseCase;
    }

    public void init(String venueCode) {
        view.init(venueCode);
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity != null) {
            ActivityUtils.onBackActivity(activity);
        }
    }

    public void createFragmentMenu(String venueCode, ViewPager viewPager) {
        List<MenuSectionModel> validMenusSections = validMenus(getMenuDbUseCase.getAllMenuName());
        if (validMenusSections != null && !validMenusSections.isEmpty()) {
            addFragmentsToPager(validMenusSections, viewPager);
        } else {
            showDialogEmptyMenu();
        }
    }

    private List<MenuSectionModel> validMenus(List<String> allMenuNames) {
        List<MenuSectionModel> validMenuSections = new ArrayList<>();
        for (String menuName : allMenuNames) {

            MenuSectionModel menuSectionModel = new MenuSectionModel();
            menuSectionModel.setMenuName(menuName);
            menuSectionModel.setMenuSections(getMenuDbUseCase.getAllMenuItemByMenuName(menuName));

            if (menuSectionModel.getMenuSections().size() > MINIMUM_SECTIONS) {
                validMenuSections.add(menuSectionModel);
            }
        }
        return validMenuSections;
    }

    private void addFragmentsToPager(List<MenuSectionModel> menuSections, ViewPager viewPager) {
        DiningMenuPagerAdapter adapter = new DiningMenuPagerAdapter(view.getFragmentManager());
        for (MenuSectionModel menuSection : menuSections) {
            adapter.addFragment(MenuListFragment.newInstance(menuSection), menuSection.getMenuName());
        }
        viewPager.setAdapter(adapter);
    }

    private void showDialogEmptyMenu() {
        Context context = view.getContext();
        if (view.getActivity() != null && context != null) {
            String message = context.getResources().getString(R.string.menu_empty_dialog_message);
            String buttonMessage = context.getResources().getString(R.string.menu_empty_dialog_button);
            DialogInterface.OnClickListener listener = (dialog, which) -> view.getActivity().finish();
            AlertDialogUtils.showDialog(view.getActivity(), listener, message, buttonMessage);
        }
    }
}
