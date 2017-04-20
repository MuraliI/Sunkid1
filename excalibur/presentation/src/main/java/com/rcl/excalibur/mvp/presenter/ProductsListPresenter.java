package com.rcl.excalibur.mvp.presenter;


import android.support.v4.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.fragments.ProductsListFragment;
import com.rcl.excalibur.mvp.view.ProductsListView;
import com.rcl.excalibur.utils.ActivityUtils;

import java.util.List;


public class ProductsListPresenter {
    private GetProductDbUseCase getProductDbUseCase;
    private ProductsListView view;

    public ProductsListPresenter(ProductsListView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init(int type) {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setAdapterObserver(new AdapterObserver(this));
        view.init();
        showCollectionInView(getProductDbUseCase.getAll(getType(activity, type)));
    }

    private String getType(final BaseActivity activity, int type) {
        String categorySelected;
        switch (type) {
            case ProductsListFragment.ROYAL_ACTIVITY:
                categorySelected = activity.getString(R.string.category_royal_activity);
                break;
            case ProductsListFragment.DINING:
                categorySelected = activity.getString(R.string.category_dining);
                break;
            case ProductsListFragment.SHOPPING:
                categorySelected = activity.getString(R.string.category_shopping);
                break;
            case ProductsListFragment.SPA:
                categorySelected = activity.getString(R.string.category_spa);
                break;
            case ProductsListFragment.SHOREX:
                categorySelected = activity.getString(R.string.category_shorex);
                break;
            case ProductsListFragment.ENTERTAINMENT:
                categorySelected = activity.getString(R.string.category_entertainment);
                break;
            default:
                categorySelected = activity.getString(R.string.category_royal_activity);
        }

        return categorySelected;
    }

    private void showCollectionInView(List<Product> products) {
        if (products == null || products.size() == 0) {
            Toast.makeText(view.getActivity(), R.string.no_items_to_show, Toast.LENGTH_LONG).show();
        } else {
            view.addAll(products);
        }
    }

    private class AdapterObserver extends DefaultPresentObserver<Pair<Product, View>, ProductsListPresenter> {

        AdapterObserver(ProductsListPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(Pair<Product, View> value) {
            BaseActivity activity = view.getActivity();
            if (activity != null) {
                ActivityUtils.startActivityWithSharedElement(activity
                        , ProductDetailActivity.getIntent(activity, value.first.getProductId())
                        , value.second
                        , activity.getString(R.string.shared_element_transition_name));
            }
        }
    }
}
