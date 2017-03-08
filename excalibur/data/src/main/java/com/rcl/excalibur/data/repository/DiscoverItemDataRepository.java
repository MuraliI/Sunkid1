package com.rcl.excalibur.data.repository;


import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.DiscoverItemEntity;
import com.rcl.excalibur.data.entity.mapper.DiscoverEntityDataMapper;
import com.rcl.excalibur.data.utils.DBUtil;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

@Singleton
public class DiscoverItemDataRepository implements DiscoverItemRepository {

    private final DiscoverEntityDataMapper discoverEntityDataMapper;

    @Inject
    DiscoverItemDataRepository(DiscoverEntityDataMapper discoverEntityDataMapper) {
        this.discoverEntityDataMapper = discoverEntityDataMapper;
    }


    @Override
    public Observable<List<DiscoverItem>> listBasicBy(String type) {
        final String[] columns = new String[8];
        columns[0] = DBUtil.COL_ID;
        columns[1] = DiscoverItemEntity.COLUMN_DISCOVER_ITEM_ID;
        columns[2] = DiscoverItemEntity.COLUMN_CATEGORY;
        columns[3] = DiscoverItemEntity.COLUMN_HOURS;
        columns[4] = DiscoverItemEntity.COLUMN_IMAGE_URL;
        columns[5] = DiscoverItemEntity.COLUMN_TITLE;
        columns[6] = DiscoverItemEntity.COLUMN_TYPE;
        columns[7] = DiscoverItemEntity.COLUMN_PROMOTION_TEXT;

        final List<DiscoverItemEntity> entities = new Select(columns)
                .from(DiscoverItemEntity.class)
                .where(eq(DiscoverItemEntity.COLUMN_TYPE, type))
                .execute();

        return Observable.create(e -> {
            e.onNext(discoverEntityDataMapper.transform(entities));
            e.onComplete();
        });
    }

    @Override
    public Observable<DiscoverItem> getFullBy(String discoverItemId) {
        final DiscoverItemEntity entity = new Select()
                .from(DiscoverItemEntity.class)
                .where(eq(DiscoverItemEntity.COLUMN_DISCOVER_ITEM_ID, discoverItemId))
                .executeSingle();
        return Observable.create(e -> {
            e.onNext(discoverEntityDataMapper.transform(entity));
            e.onComplete();
        });
    }
}
