package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.repository.ItemRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

@Deprecated
public class GetItemList extends UseCaseSync {

    private final ItemRepository itemRepository;

    @Inject
    GetItemList(ItemRepository itemRepository) {
        super();
        this.itemRepository = itemRepository;
    }

    void buildUseCaseObservable(DisposableObserver<List<DiscoverItem>> observer, Void params) {
        this.itemRepository.items();
    }
}
