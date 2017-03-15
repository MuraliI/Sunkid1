package com.rcl.excalibur.mvp.presenter;


import android.content.res.Resources;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DiscoverItemDetailActivity;
import com.rcl.excalibur.adapters.delegate.factory.DinningDetailModuleFactory;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.interactor.DiscoverItemDbUseCase;
import com.rcl.excalibur.mapper.DiscoverModelDataMapper;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.DiscoverItemDetailView;

import javax.inject.Inject;

public class DiscoverItemDetailPresenter implements BasePresenter {
    @Inject DiscoverItemDbUseCase discoverItemDbUseCase;
    private DiscoverModelDataMapper discoverModelDataMapper;
    private DiscoverItemDetailView view;

    public DiscoverItemDetailPresenter(DiscoverItemDetailView view, int discoverItemId) {
        this.discoverModelDataMapper = new DiscoverModelDataMapper();
        this.view = view;
        initInjection();
        initView(discoverItemId);
    }

    private void initInjection() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
    }

    private void initView(final int discoverItemId) {
        if (view.getActivity() != null) {
            view.setDetailTitle(view.getActivity().getString(R.string.hardcoded_activity_title)); //FIXME get the plan title
        }
        view.setAdapterObserver(new DetailAdapterObserver(this));

        final DiscoverItem discoverItem = discoverItemDbUseCase.getDiscoverItem(discoverItemId);
        showItemInView(discoverItem);
    }

    private void showItemInView(DiscoverItem discoverItem) {
        final DiscoverItemDetailActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        final Resources resources = activity.getResources();
        final DiscoverItemModel discoverItemModel = discoverModelDataMapper.transform(discoverItem);
        final DinningDetailModuleFactory moduleFactory = new DinningDetailModuleFactory(discoverItemModel);

        view.render(moduleFactory.getDelegateAdapterArray(), moduleFactory.getListOfDetailViewTypes(resources));

    }


    public class DetailAdapterObserver extends DefaultPresentObserver<DiscoverItemModel, DiscoverItemDetailPresenter> {

        public DetailAdapterObserver(DiscoverItemDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(DiscoverItemModel value) {
            //TODO do something when a detail item is clicked.
        }
    }

}
