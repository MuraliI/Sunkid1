package com.rcl.excalibur.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.presenter.MenuListPresenter;
import com.rcl.excalibur.mvp.view.MenuListView;

public class MenuListFragment extends Fragment {

    protected MenuListPresenter presenter;
    private static final String ARGUMENT_DINING_MENU_ID = "MenuListFragment.ARGUMENT_DINING_MENU_ID";

    public static MenuListFragment newInstance(String idTypeMenu) {
        final MenuListFragment fragment = new MenuListFragment();
        final Bundle args = new Bundle();
        args.putString(ARGUMENT_DINING_MENU_ID, idTypeMenu);
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
        if (bundle == null || !bundle.containsKey(ARGUMENT_DINING_MENU_ID)) {
            return;
        }
        final String idTypeMenu = bundle.getString(ARGUMENT_DINING_MENU_ID);
        presenter = new MenuListPresenter(new MenuListView(this), new GetProductDbUseCase(new ProductDataRepository()));
        presenter.init(idTypeMenu);
    }
}
