package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.DiscoverAdapter;
import com.rcl.excalibur.fragments.DiscoverItemListFragment;
import com.rcl.excalibur.model.DiscoverItemModel;

import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscoverItemListView extends FragmentView<DiscoverItemListFragment> {
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private DiscoverAdapter adapter;

    public DiscoverItemListView(DiscoverItemListFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        adapter = new DiscoverAdapter(adapterObserver);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    public void addAll(Collection<DiscoverItemModel> list) {
        adapter.addAll(list);
    }
}
