package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.domain.Item;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetItemList;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mapper.ItemModelDataMapper;
import com.rcl.excalibur.mvp.view.LoadFromDBView;
import com.rcl.excalibur.model.ItemModel;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class LoadFromDBPresenter implements BasePresenter {

    protected ItemModelDataMapper itemModelDataMapper;
    @Inject GetItemList getItemListUseCase;
    private LoadFromDBView view;

    public LoadFromDBPresenter(LoadFromDBView view) {
        this.view = view;
        itemModelDataMapper = new ItemModelDataMapper();
        initInjection();
        init();
    }

    private void initInjection() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
    }

    private void init() {
        view.setViewObserver(new ViewObserver(this));
        view.setAdapterObserver(new AdapterObserver(this));
    }

    private void getUserList() {
        this.getItemListUseCase.execute(new ItemListObserver(), null);
    }

    protected void showUsersCollectionInView(Collection<Item> itemCollection) {
        final Collection<ItemModel> itemModelsCollection = this.itemModelDataMapper.transform(itemCollection);
        this.view.render(itemModelsCollection);
    }

    public class ViewObserver extends DefaultPresentObserver<String, LoadFromDBPresenter> {
        public ViewObserver(LoadFromDBPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(String value) {
            LoadFromDBPresenter.this.getUserList();
        }
    }

    public class AdapterObserver extends DefaultPresentObserver<ItemModel, LoadFromDBPresenter> {

        public AdapterObserver(LoadFromDBPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(ItemModel value) {
            view.showMessage(value.getName());
        }
    }

    private final class ItemListObserver extends DefaultObserver<List<Item>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(List<Item> items) {
            showUsersCollectionInView(items);
        }
    }
}
