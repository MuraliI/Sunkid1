package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Item;
import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.repository.ItemRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Item}.
 */
public class GetItemList extends UseCase<List<Item>, Void> {

    private final ItemRepository itemRepository;

    @Inject
    GetItemList(ItemRepository itemRepository, ThreadExecutor threadExecutor,
                PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.itemRepository = itemRepository;
    }

    @Override
    Observable<List<Item>> buildUseCaseObservable(Void unused) {
        return this.itemRepository.items();
    }
}
