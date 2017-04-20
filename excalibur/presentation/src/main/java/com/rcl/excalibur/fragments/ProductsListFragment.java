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
import com.rcl.excalibur.mvp.presenter.ProductsListPresenter;
import com.rcl.excalibur.mvp.view.ProductsListView;

public class ProductsListFragment extends Fragment {
    public static final int ROYAL_ACTIVITY = 0;
    public static final int DINING = 1;
    public static final int SHOPPING = 2;
    public static final int SPA = 3;
    public static final int SHOREX = 4;
    public static final int ENTERTAINMENT = 5;
    private static final String ARGUMENT_TYPE = "ProductsListFragment.ARGUMENT_TYPE";
    private static final String ARGUMENT_CATEGORY_ID = "ProductsListFragment.ARGUMENT_CATEGORY_ID";

    protected ProductsListPresenter presenter;

    public static ProductsListFragment newInstance(int type, String categoryId) {
        final ProductsListFragment fragment = new ProductsListFragment();
        final Bundle args = new Bundle();
        args.putInt(ARGUMENT_TYPE, type);
        args.putString(ARGUMENT_CATEGORY_ID, categoryId);

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
        final Bundle bundle = getArguments();
        if (bundle == null || !bundle.containsKey(ARGUMENT_TYPE)) {
            return;
        }
        final int productType = bundle.getInt(ARGUMENT_TYPE);
        final String categoryId = bundle.getString(ARGUMENT_CATEGORY_ID);

        presenter = new ProductsListPresenter(new ProductsListView(this),
                new GetProductDbUseCase(new ProductDataRepository()));
        presenter.init(productType, categoryId);
    }
}
