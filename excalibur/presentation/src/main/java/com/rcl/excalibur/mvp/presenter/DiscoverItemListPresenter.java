package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DiscoverItemDetailActivity;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.fragments.DiscoverItemListFragment;
import com.rcl.excalibur.mvp.view.ProductsListView;

import java.util.List;

import javax.inject.Inject;


public class DiscoverItemListPresenter implements BasePresenter {
    @Inject GetProductDbUseCase getProductDbUseCase;
    private ProductsListView view;
    private int type;

    public DiscoverItemListPresenter(int type, ProductsListView view) {
        this.view = view;
        this.type = type;
        initInjection();
        init();
    }

    private void init() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setAdapterObserver(new AdapterObserver(this));
        view.init();
        final String type = getType(activity, this.type);
        showCollectionInView(getProductDbUseCase.getAll(type));
    }

    private String getType(final BaseActivity activity, int type) {
        switch (type) {
            case DiscoverItemListFragment.ROYAL_ACTIVITY:
                return activity.getString(R.string.category_royal_activity);
            case DiscoverItemListFragment.DINING:
                return activity.getString(R.string.category_dining);
            case DiscoverItemListFragment.SHOPPING:
                return activity.getString(R.string.category_shopping);
            case DiscoverItemListFragment.SPA:
                return activity.getString(R.string.category_spa);
            case DiscoverItemListFragment.SHOREX:
                return activity.getString(R.string.category_shorex);
            case DiscoverItemListFragment.ENTERTAINMENT:
                return activity.getString(R.string.category_entertainment);
            default:
                return activity.getString(R.string.category_royal_activity);
        }
    }

    private void initInjection() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
    }

    private void showCollectionInView(List<Product> products) {
        view.addAll(products);
    }

    public class AdapterObserver extends DefaultPresentObserver<Product, DiscoverItemListPresenter> {

        AdapterObserver(DiscoverItemListPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(Product value) {
            BaseActivity activity = view.getActivity();
            if (activity != null) {
                activity.startActivity(DiscoverItemDetailActivity.getIntent(activity, value.getProductId()));
            }
        }
    }
}
