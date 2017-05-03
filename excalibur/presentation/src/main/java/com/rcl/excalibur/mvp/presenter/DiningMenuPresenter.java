package com.rcl.excalibur.mvp.presenter;

import android.content.DialogInterface;
import android.support.v4.view.ViewPager;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.DiningMenuPagerAdapter;
import com.rcl.excalibur.data.repository.MenuDataRepository;
import com.rcl.excalibur.domain.interactor.GetMenuDbUseCase;
import com.rcl.excalibur.fragments.MenuListFragment;
import com.rcl.excalibur.mvp.view.DiningMenuView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.AlertDialogUtils;

import java.util.List;

public class DiningMenuPresenter {

    private final DiningMenuView view;

    public DiningMenuPresenter(DiningMenuView view) {
        this.view = view;
    }

    public void init(String venueCode) {
        view.init(venueCode);
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    public void createFragmentMenu(String venueCode, ViewPager viewPager) {
        GetMenuDbUseCase getMenuDbUseCase = new GetMenuDbUseCase(new MenuDataRepository());
        List<String> menusName = getMenuDbUseCase.getAllMenuName();
        if (menusName != null && !menusName.isEmpty()) {
            addFragmentsToPager(menusName, viewPager);
        } else {
            showDialogEmptyMenu();
        }
    }

    private void addFragmentsToPager(List<String> menusName, ViewPager viewPager) {
        DiningMenuPagerAdapter adapter = new DiningMenuPagerAdapter(view.getFragmentManager());
        for (String menuName : menusName) {
            adapter.addFragment(MenuListFragment.newInstance(menuName), menuName);
        }
        viewPager.setAdapter(adapter);
    }

    private void showDialogEmptyMenu() {
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
