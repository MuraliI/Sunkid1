package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.ProductsAdapter;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.fragments.ProductsListFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductsListView extends FragmentView<ProductsListFragment, Void, Pair<Product, View>> {
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private ProductsAdapter adapter;

    public ProductsListView(ProductsListFragment fragment) {
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
