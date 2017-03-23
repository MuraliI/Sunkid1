package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DiscoverItemDetailActivity;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.fragments.DiscoverItemListFragment;
import com.rcl.excalibur.mapper.DiscoverModelDataMapper;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.DiscoverItemListView;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;


public class DiscoverItemListPresenter implements BasePresenter {
    protected DiscoverModelDataMapper discoverModelDataMapper;
    @Inject GetProductDbUseCase getProductDbUseCase;
    private DiscoverItemListView view;
    private int type;

    public DiscoverItemListPresenter(int type, DiscoverItemListView view) {
        this.discoverModelDataMapper = new DiscoverModelDataMapper();
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

        AnalyticsUtils.trackState(AnalyticsConstants.KEY_DISCOVER);

        view.setAdapterObserver(new AdapterObserver(this));
        view.init();
        final String type = getType(activity, this.type);

        final List<Product> products = getProductDbUseCase.getAll(type);
//  TODO      showCollectionInView(discoverItems);
    }

    protected String getType(final BaseActivity activity, int type) {
        AnalyticEvent analyticEvent = new AnalyticEvent(AnalyticsConstants.KEY_FILTER_DISCOVER);

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

        AnalyticsUtils.trackEvent(analyticEvent.addKeyValue(AnalyticsConstants.KEY_FILTER_CATEGORY, categorySelected));
        return categorySelected;
    }

    private void initInjection() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
    }

    protected void showCollectionInView(List<DiscoverItem> discoverItems) {
        final Collection<DiscoverItemModel> models = discoverModelDataMapper.transform(discoverItems);
        view.addAll(models);

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
