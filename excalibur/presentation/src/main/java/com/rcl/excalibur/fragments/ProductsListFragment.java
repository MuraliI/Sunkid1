package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.internal.di.component.FragmentComponent;
import com.rcl.excalibur.internal.di.component.products.ProductsListFragmentComponent;
import com.rcl.excalibur.internal.di.module.products.ProductsListFragmentModule;
import com.rcl.excalibur.mvp.presenter.ProductsListPresenter;

public class ProductsListFragment extends BaseFragment<ProductsListPresenter> {
    public static final int ROYAL_ACTIVITY = 0;
    public static final int DINING = 1;
    public static final int SHOPPING = 2;
    public static final int SPA = 3;
    public static final int SHOREX = 4;
    public static final int ENTERTAINMENT = 5;
    public static final String ARGUMENT_TYPE = "ProductsListFragment.ARGUMENT_TYPE";
    private ProductsListFragmentComponent productsListFragmentComponent;

    public static ProductsListFragment newInstance(int type) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init();
    }

    @Override
    protected void createFragmentComponent() {
        final Bundle bundle = getArguments();
        if (bundle == null || !bundle.containsKey(ARGUMENT_TYPE)) {
            return;
        }
        final int productType = bundle.getInt(ARGUMENT_TYPE);
        productsListFragmentComponent = ((PlanListActivity) getActivity()).getProductsListActivityComponent()
                .plus(new ProductsListFragmentModule(this, productType));
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        productsListFragmentComponent.inject(this);
    }

    @Override
    protected void destroyFragmentComponent() {
        super.destroyFragmentComponent();
        productsListFragmentComponent = null;
    }
}
