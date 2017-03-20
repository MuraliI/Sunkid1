package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DiscoverItemDetailActivity;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetDiscoverList;
import com.rcl.excalibur.fragments.DiscoverFragment;
import com.rcl.excalibur.mapper.DiscoverModelDataMapper;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.DiscoverView;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import static com.rcl.excalibur.domain.interactor.GetDiscoverList.Params;


public class DiscoverPresenter implements BasePresenter {
    protected DiscoverModelDataMapper discoverModelDataMapper;
    @Inject GetDiscoverList getDiscoverList;
    private DiscoverView view;
    private int type;

    public DiscoverPresenter(int type, DiscoverView view) {
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
        getDiscoverList.execute(new DiscoverListObserver(), Params.create(type));
    }

    protected String getType(final BaseActivity activity, int type) {
        AnalyticEvent analyticEvent = new AnalyticEvent(AnalyticsConstants.KEY_FILTER_DISCOVER);

        String categorySelected;
        switch (type) {
            case DiscoverFragment.ROYAL_ACTIVITY:
                categorySelected = activity.getString(R.string.category_royal_activity);
                break;
            case DiscoverFragment.DINING:
                categorySelected = activity.getString(R.string.category_dining);
                break;
            case DiscoverFragment.SHOPPING:
                categorySelected = activity.getString(R.string.category_shopping);
                break;
            case DiscoverFragment.SPA:
                categorySelected = activity.getString(R.string.category_spa);
                break;
            case DiscoverFragment.SHOREX:
                categorySelected = activity.getString(R.string.category_shorex);
                break;
            case DiscoverFragment.ENTERTAINMENT:
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

    public class AdapterObserver extends DefaultPresentObserver<DiscoverItemModel, DiscoverPresenter> {

        public AdapterObserver(DiscoverPresenter presenter) {
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

    public final class DiscoverListObserver extends DefaultObserver<List<DiscoverItem>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(List<DiscoverItem> discoverItems) {
            showCollectionInView(discoverItems);
        }
    }
}
