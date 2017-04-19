package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.DiscoverItemEntity;
import com.rcl.excalibur.data.mapper.DiscoverEntityDataMapper;
import com.rcl.excalibur.data.utils.DBUtil;
import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;

import java.util.List;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

public class DiscoverItemDataRepository extends BaseDataRepository<DiscoverItem, DiscoverItemEntity, Void, DiscoverEntityDataMapper>
        implements DiscoverItemRepository {

    public DiscoverItemDataRepository() {
        super(new DiscoverEntityDataMapper(), DiscoverItemEntity.class);
    }

    @Override
    public List<DiscoverItem> listAll(String type) {
        final String[] columns = new String[]{
                DBUtil.COL_ID,
                DiscoverItemEntity.COLUMN_DISCOVER_ITEM_ID,
                DiscoverItemEntity.COLUMN_CATEGORY,
                DiscoverItemEntity.COLUMN_HOURS,
                DiscoverItemEntity.COLUMN_IMAGE_URL,
                DiscoverItemEntity.COLUMN_TITLE,
                DiscoverItemEntity.COLUMN_TYPE,
        };
        final List<DiscoverItemEntity> entities = new Select(columns)
                .from(DiscoverItemEntity.class)
                .where(eq(DiscoverItemEntity.COLUMN_TYPE, type))
                .execute();
        return getMapper().transform(entities, null);

    }

    @Override
    public DiscoverItem get(String id) {
        return get(DiscoverItemEntity.COLUMN_DISCOVER_ITEM_ID, id);
    }

    @Override
    public void create(List<DiscoverItem> discoverItems) {
        Preconditions.unreachable();
    }

    @Override
    public void deleteAll() {
        // no op
    }

    @Override
    public void create(@NonNull DiscoverItem promotion) {
        Preconditions.unreachable();
    }
}
