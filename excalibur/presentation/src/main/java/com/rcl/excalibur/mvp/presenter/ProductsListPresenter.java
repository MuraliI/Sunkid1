package com.rcl.excalibur.mvp.presenter;


import android.support.v4.util.Pair;
import android.view.View;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.domain.ChildCategory;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.fragments.ProductsListFragment;
import com.rcl.excalibur.mvp.view.ProductsListView;
import com.rcl.excalibur.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;


public class ProductsListPresenter {
    private GetProductDbUseCase getProductDbUseCase;
    private ProductsListView view;

    public ProductsListPresenter(ProductsListView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init(int type, String categoryId) {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setAdapterObserver(new AdapterObserver(this));
        view.init(pair -> showCollectionInView(getProductsByCategory(type, categoryId, pair.first, pair.second, activity)));
    }

    private List<Product> getProductsByCategory(int type, String categoryId, int offset, int maxCount, BaseActivity activity) {
        List<Product> childProducts = new ArrayList<>();
        String typeQuery = getType(activity, type);
        List<Product> allProducts = getProductDbUseCase.getByType(typeQuery, maxCount, offset);

        if (categoryId == null) {
            childProducts = allProducts;
        } else {
            for (Product typeProduct : allProducts) {
                for (ChildCategory childCategory : typeProduct.getProductCategory().getChildCategory()) {
                    if (categoryId.equals(childCategory.getItems().getCategoryId())) {
                        childProducts.add(typeProduct);
                    }
                }
            }
        }
        return childProducts;
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
            case ProductsListFragment.GUEST_SERVICES:
                categorySelected = activity.getString(R.string.category_guest_services);
                break;
            default:
                categorySelected = activity.getString(R.string.category_royal_activity);
        }

        return categorySelected;
    }

    private void showCollectionInView(List<Product> products) {
        if (products == null || products.size() == 0) {
            view.addAlertNoProducts();
        } else {
            view.add(products);
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
