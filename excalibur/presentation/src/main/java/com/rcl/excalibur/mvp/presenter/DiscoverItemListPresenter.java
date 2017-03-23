package com.rcl.excalibur.mvp.presenter;


import android.widget.Toast;

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

    protected String getType(final BaseActivity activity, int type) {

        String categorySelected;
        switch (type) {
            case DiscoverItemListFragment.ROYAL_ACTIVITY:
                categorySelected = activity.getString(R.string.category_royal_activity);
                break;
            case DiscoverItemListFragment.DINING:
                categorySelected = activity.getString(R.string.category_dining);
                break;
            case DiscoverItemListFragment.SHOPPING:
                categorySelected = activity.getString(R.string.category_shopping);
                break;
            case DiscoverItemListFragment.SPA:
                categorySelected = activity.getString(R.string.category_spa);
                break;
            case DiscoverItemListFragment.SHOREX:
                categorySelected = activity.getString(R.string.category_shorex);
                break;
            case DiscoverItemListFragment.ENTERTAINMENT:
                categorySelected = activity.getString(R.string.category_entertainment);
                break;
            default:
                categorySelected = activity.getString(R.string.category_royal_activity);
        }

        return categorySelected;
    }

    private void initInjection() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
    }

    protected void showCollectionInView(List<Product> products) {
        if (products == null || products.size() == 0) {
            Toast.makeText(view.getActivity(), R.string.no_items_to_show, Toast.LENGTH_LONG).show();
        } else {
            view.addAll(products);
        }
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
