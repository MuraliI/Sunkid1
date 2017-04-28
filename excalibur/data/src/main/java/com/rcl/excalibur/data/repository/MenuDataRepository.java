package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.MenuEntity;
import com.rcl.excalibur.data.entity.TypeEntity;
import com.rcl.excalibur.data.mapper.MediaEntityDataMapper;
import com.rcl.excalibur.data.mapper.MenuEntityDataMapper;
import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;

public class MenuDataRepository extends BaseDataRepository<Menu, MenuEntity, Void, MenuEntityDataMapper>
        implements MenuRepository {

    public MenuDataRepository() {
        super(new MenuEntityDataMapper(new MediaEntityDataMapper()), MenuEntity.class);

    }


    @Override
    public void create(@NonNull Menu input) {
        final MenuEntity entity = new MenuEntity();
        entity.setDayNumber(input.getDayNumber());
        entity.setMenuName(input.getMenuName());
        entity.save();
    }

    @Override
    public List<Menu> getAll() {
        final MenuEntity typeEntity = new Select()
                .from(TypeEntity.class)
                .executeSingle();
        if (typeEntity == null) {
            return new ArrayList<>();
        }
        final List<MenuEntity> entities = new Select()
                .from(MenuEntity.class)
                .execute();
        return getMapper().transform(entities, null);
    }

    @Override
    public void deleteAll() {
        new Delete().from(MenuEntity.class).execute();

    }
}
