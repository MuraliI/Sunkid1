package com.rcl.excalibur.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.MenuSectionModel;
import com.rcl.excalibur.mvp.presenter.MenuListPresenter;
import com.rcl.excalibur.mvp.view.MenuListView;

public class MenuListFragment extends Fragment {

    protected MenuListPresenter presenter;
    private static final String ARGUMENT_DINING_MENU_SECTION = "MenuListFragment.ARGUMENT_DINING_MENU_SECTION";

    public static MenuListFragment newInstance(MenuSectionModel menuName) {
        final MenuListFragment fragment = new MenuListFragment();
        final Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_DINING_MENU_SECTION, menuName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_dining_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle bundle = getArguments();
        if (bundle == null || !bundle.containsKey(ARGUMENT_DINING_MENU_SECTION)) {
            return;
        }
        final MenuSectionModel menuSectionModel = bundle.getParcelable(ARGUMENT_DINING_MENU_SECTION);
        presenter = new MenuListPresenter(new MenuListView(this));
        presenter.init(menuSectionModel);
    }
}
