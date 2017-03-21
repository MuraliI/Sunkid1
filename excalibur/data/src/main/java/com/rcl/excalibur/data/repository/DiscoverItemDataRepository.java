package com.rcl.excalibur.data.repository;


import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.DiscoverItemEntity;
import com.rcl.excalibur.data.mapper.DiscoverEntityDataMapper;
import com.rcl.excalibur.data.service.DiscoverServiceImpl;
import com.rcl.excalibur.data.utils.DBUtil;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

@Singleton
public class DiscoverItemDataRepository implements DiscoverItemRepository {

    private final DiscoverEntityDataMapper discoverEntityDataMapper;

    @Inject
    DiscoverItemDataRepository(DiscoverEntityDataMapper discoverEntityDataMapper) {
        this.discoverEntityDataMapper = discoverEntityDataMapper;
    }

    @Override
    public List<DiscoverItem> listAll(String type) {

        DiscoverServiceImpl discoverService = new DiscoverServiceImpl();
        discoverService.getSpas();
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
        return discoverEntityDataMapper.transform(entities);

    }

    @Override
    public DiscoverItem get(int id) {
        final DiscoverItemEntity entity = new Select()
                .from(DiscoverItemEntity.class)
                .where(eq(DiscoverItemEntity.COLUMN_DISCOVER_ITEM_ID, id))
                .executeSingle();
        return discoverEntityDataMapper.transform(entity);
    }

    @Override
    public void create(List<DiscoverItem> discoverItems) {
    }
}
