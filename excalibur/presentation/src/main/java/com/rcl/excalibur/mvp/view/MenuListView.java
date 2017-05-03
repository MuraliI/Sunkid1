package com.rcl.excalibur.mvp.view;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.DiningMenuCardAdapter;
import com.rcl.excalibur.domain.MenuItem;
import com.rcl.excalibur.fragments.MenuListFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuListView extends FragmentView<MenuListFragment, Void, Void> {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private DiningMenuCardAdapter adapter;

    public MenuListView(MenuListFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        adapter = new DiningMenuCardAdapter(adapterObserver);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    public void addAll(List<MenuItem> menus) {
        adapter.addAll(menus);
    }

}
