package com.rcl.excalibur.data.repository;


import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.DiscoverItemEntity;
import com.rcl.excalibur.data.entity.mapper.DiscoverEntityDataMapper;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.repository.DiscoverRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

@Singleton
public class DiscoverDataRepository implements DiscoverRepository {

    private final DiscoverEntityDataMapper discoverEntityDataMapper;

    @Inject
    DiscoverDataRepository(DiscoverEntityDataMapper discoverEntityDataMapper) {
        this.discoverEntityDataMapper = discoverEntityDataMapper;
    }


    @Override
    public Observable<List<DiscoverItem>> listBy(String type) {
        final List<DiscoverItemEntity> entities = new Select()
                .from(DiscoverItemEntity.class)
                .where(eq(DiscoverItemEntity.COLUMN_TYPE, type))
                .execute();

        return Observable.create(e -> {
            e.onNext(discoverEntityDataMapper.transform(entities));
            e.onComplete();
        });
    }
}
