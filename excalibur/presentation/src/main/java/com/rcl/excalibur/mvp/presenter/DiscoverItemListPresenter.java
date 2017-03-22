package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DiscoverItemDetailActivity;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.interactor.GetDiscoverItemDbUseCase;
import com.rcl.excalibur.fragments.DiscoverItemListFragment;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.ProductsListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class DiscoverItemListPresenter implements BasePresenter {
    @Inject GetDiscoverItemDbUseCase getDiscoverItemDbUseCase;
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

        final List<DiscoverItem> discoverItems = getDiscoverItemDbUseCase.getAllDiscoverItem(type);
        showCollectionInView(discoverItems);
    }

    protected String getType(final BaseActivity activity, int type) {
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

    protected void showCollectionInView(List<DiscoverItem> discoverItems) {
        // TODO: 22/03/17 Pass products from the use case
        view.addAll(new ArrayList<>());
    }

    public class AdapterObserver extends DefaultPresentObserver<DiscoverItemModel, DiscoverItemListPresenter> {

        public AdapterObserver(DiscoverItemListPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {
            BaseActivity activity = view.getActivity();
            if (activity != null) {
                activity.startActivity(DiscoverItemDetailActivity.getIntent(activity, value.getDiscoverId()));
            }
        }
    }
}
