package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.ProductsAdapter;
import com.rcl.excalibur.adapters.base.LoadMoreScrollListener;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.fragments.ProductsListFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class ProductsListView extends FragmentView<ProductsListFragment, Void, Pair<Product, View>> {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.alert_no_products) RelativeLayout alertNoProducts;

    private ProductsAdapter adapter;
    LoadMoreScrollListener loadMoreScrollListener;
    private PublishSubject<Pair<Integer, Integer>> publisherSubject = PublishSubject.create();
    public static final int START_OFFSET = 0;

    public ProductsListView(ProductsListFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init(Consumer<Pair<Integer, Integer>> pairConsumer) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        adapter = new ProductsAdapter(adapterObserver);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);

        publisherSubject.subscribe(pairConsumer);
        loadMoreScrollListener = new LoadMoreScrollListener(recyclerView.getLayoutManager(), START_OFFSET) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                publisherSubject.onNext(new Pair<>(page, LoadMoreScrollListener.MAX_COUNT));
            }
        };

        recyclerView.addOnScrollListener(loadMoreScrollListener);

        //First Call
        publisherSubject.onNext(new Pair<>(START_OFFSET, LoadMoreScrollListener.MAX_COUNT));
    }

    public void addAll(List<Product> list) {
        adapter.addAll(list);
    }

    public void add(List<Product> list) {
        adapter.add(list);
    }

    public void addAlertNoProducts() {
        alertNoProducts.setVisibility(View.VISIBLE);
    }
}
