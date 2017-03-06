package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetDiscoverList;
import com.rcl.excalibur.fragments.DiscoverFragment;
import com.rcl.excalibur.mapper.DiscoverModelDataMapper;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.DiscoverView;

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
        view.setAdapterObserver(new AdapterObserver(this));
        view.init();
        final String type = getType(activity, this.type);
        getDiscoverList.execute(new DiscoverListObserver(), Params.create(type));
    }

    protected String getType(final BaseActivity activity, int type) {
        switch (type) {
            case DiscoverFragment.ROYAL_ACTIVITY:
                return activity.getString(R.string.category_royal_activity);
            case DiscoverFragment.DINING:
                return activity.getString(R.string.category_dining);
            case DiscoverFragment.SHOPPING:
                return activity.getString(R.string.category_shopping);
            case DiscoverFragment.SPA:
                return activity.getString(R.string.category_spa);
            case DiscoverFragment.SHOREX:
                return activity.getString(R.string.category_shorex);
            case DiscoverFragment.ENTERTAINMENT:
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

    public class AdapterObserver extends DefaultPresentObserver<DiscoverItemModel, DiscoverPresenter> {

        public AdapterObserver(DiscoverPresenter presenter) {
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
