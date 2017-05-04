package com.rcl.excalibur.mvp.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.ViewPager;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.DiningMenuPagerAdapter;
import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.interactor.GetMenuDbUseCase;
import com.rcl.excalibur.domain.interactor.GetMenusUseCase;
import com.rcl.excalibur.fragments.MenuListFragment;
import com.rcl.excalibur.mvp.view.DiningMenuView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.AlertDialogUtils;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class DiningMenuPresenter {

    private final DiningMenuView view;
    private final GetMenuDbUseCase getMenuDbUseCase;
    private final GetMenusUseCase getMenusUseCase;

    public DiningMenuPresenter(DiningMenuView view,
                               GetMenuDbUseCase getMenuDbUseCase,
                               GetMenusUseCase getMenusUseCase) {
        this.view = view;
        this.getMenuDbUseCase = getMenuDbUseCase;
        this.getMenusUseCase = getMenusUseCase;
    }

    public void init() {
        getMenusUseCase.execute(new DisposableObserver<List<Menu>>() {
            @Override
            public void onNext(List<Menu> value) {
            }

            @Override
            public void onError(Throwable e) {
                showDialogEmptyMenu();
            }

            @Override
            public void onComplete() {
                view.init();
            }
        }, null);
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity != null) {
            ActivityUtils.onBackActivity(activity);
        }
    }

    public void createFragmentMenu(ViewPager viewPager) {
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
        Context context = view.getContext();
        if (view.getActivity() != null && context != null) {
            String message = context.getResources().getString(R.string.menu_empty_dialog_message);
            String buttonMessage = context.getResources().getString(R.string.menu_empty_dialog_button);
            DialogInterface.OnClickListener listener = (dialog, which) -> view.getActivity().finish();
            AlertDialogUtils.showDialog(view.getActivity(), listener, message, buttonMessage);
        }
    }
}
