package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetDiscoverItemBasicList;
import com.rcl.excalibur.fragments.DiscoverItemListFragment;
import com.rcl.excalibur.mapper.DiscoverModelDataMapper;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.DiscoverItemListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import static com.rcl.excalibur.domain.interactor.GetDiscoverItemBasicList.Params;


public class DiscoverItemListPresenter implements BasePresenter {
    protected DiscoverModelDataMapper discoverModelDataMapper;
    @Inject GetDiscoverItemBasicList getDiscoverItemBasicList;
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
        view.setAdapterObserver(new AdapterObserver(this));
        view.init();
        final String type = getType(activity, this.type);
        getDiscoverItemBasicList.execute(new DiscoverListObserver(), Params.create(type));
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
        final Collection<DiscoverItemModel> models = discoverModelDataMapper.transform(discoverItems);
        view.addAll(models);

    }

    public class AdapterObserver extends DefaultPresentObserver<DiscoverItemModel, DiscoverItemListPresenter> {

        public AdapterObserver(DiscoverItemListPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {
            //TODO open Details Screen
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
