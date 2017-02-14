package com.rcl.excalibur.data.repository;


import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.ItemEntity;
import com.rcl.excalibur.data.entity.mapper.ItemEntityDataMapper;
import com.rcl.excalibur.domain.Item;
import com.rcl.excalibur.domain.repository.ItemRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class ItemDataRepository implements ItemRepository {

    private final ItemEntityDataMapper itemEntityDataMapper;

    @Inject
    ItemDataRepository(ItemEntityDataMapper itemEntityDataMapper) {
        this.itemEntityDataMapper = itemEntityDataMapper;
    }


    @Override
    public Observable<List<Item>> items() {
        List<ItemEntity> entities = new Select().from(ItemEntity.class).execute();
        return Observable.create(e -> {
            e.onNext(itemEntityDataMapper.transform(entities));
            e.onComplete();
        });
    }
}
