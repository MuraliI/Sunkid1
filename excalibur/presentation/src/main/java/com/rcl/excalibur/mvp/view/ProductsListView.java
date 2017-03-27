package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.ProductsAdapter;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.fragments.DiscoverItemListFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductsListView extends FragmentView<DiscoverItemListFragment> {
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private ProductsAdapter adapter;

    public ProductsListView(DiscoverItemListFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        adapter = new ProductsAdapter(adapterObserver);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    public void addAll(List<Product> list) {
        adapter.addAll(list);
    }
}
