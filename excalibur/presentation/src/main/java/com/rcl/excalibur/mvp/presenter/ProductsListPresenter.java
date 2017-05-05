package com.rcl.excalibur.mvp.presenter;


import android.support.v4.util.Pair;
import android.view.View;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.ChildCategory;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.fragments.ProductsListFragment;
import com.rcl.excalibur.mvp.presenter.rx.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.ProductsListView;
import com.rcl.excalibur.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.mvp.view.ProductsListView.START_OFFSET;


public class ProductsListPresenter {
    private GetProductDbUseCase getProductDbUseCase;
    private ProductsListView view;

    public ProductsListPresenter(ProductsListView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init(int category, String childCategoryId) {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setAdapterObserver(new AdapterObserver(this));
        view.init(pair -> showCollectionInView(getProductsByCategory(category, childCategoryId, pair.first, pair.second, activity), pair.first));
    }

    private List<Product> getProductsByCategory(int categoryId, String childCategoryId, int currentPage, int maxCount, BaseActivity activity) {
        List<Product> childProducts = new ArrayList<>();
        String typeQuery = getType(activity, categoryId);
        List<Product> allProducts = getProductDbUseCase.getByCategory(typeQuery, maxCount, currentPage * maxCount);

        if (childCategoryId == null) {
            childProducts = allProducts;
        } else {
            for (Product typeProduct : allProducts) {
                for (ChildCategory childCategory : typeProduct.getProductCategory().getChildCategory()) {
                    //FIXME when service category return right category
//                    if (childCategoryId.equals(childCategory.getItems().getCategoryId())) {
                    childProducts.add(typeProduct);
//                    }
                }
            }
        }
        return childProducts;
    }

    private void showCollectionInView(List<Product> products, int page) {
        boolean noResult = (products == null || products.size() == 0);
        if (noResult) {
            view.noMoreLoad();
            if (page == START_OFFSET) {
                view.addAlertNoProducts();
            }
        } else if (!noResult) {
            view.add(products);
        }
    }

    private String getType(final BaseActivity activity, int type) {
        String categorySelected;
        switch (type) {
            case ProductsListFragment.ROYAL_ACTIVITY:
                categorySelected = Category.ACTIVITIES_CATEGORY;
                break;
            case ProductsListFragment.DINING:
                categorySelected = Category.DINING_CATEGORY;
                break;
            case ProductsListFragment.SHOPPING:
                categorySelected = Category.SHOPPING_CATEGORY;
                break;
            case ProductsListFragment.SPA:
                categorySelected = Category.SPA_CATEGORY;
                break;
            case ProductsListFragment.SHOREX:
                categorySelected = Category.SHOREX_CATEGORY;
                break;
            case ProductsListFragment.ENTERTAINMENT:
                categorySelected = Category.ENTERTAINMENT_CATEGORY;
                break;
            case ProductsListFragment.GUEST_SERVICES:
                categorySelected = Category.GUEST_SERVICES_CATEGORY;
                break;
            default:
                categorySelected = activity.getString(R.string.category_royal_activity);
        }

        return categorySelected;
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
