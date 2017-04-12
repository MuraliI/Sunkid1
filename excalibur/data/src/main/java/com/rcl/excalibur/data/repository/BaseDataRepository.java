package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.mapper.BaseDataMapper;

import java.util.List;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

public abstract class BaseDataRepository<O, I extends Model, M extends BaseDataMapper<O, I>> {

    private final M dataMapper;
    private final Class<I> claz;

    protected BaseDataRepository(M dataMapper, Class<I> claz) {
        this.dataMapper = dataMapper;
        this.claz = claz;
    }

    protected BaseDataMapper<O, I> getMapper() {
        return dataMapper;
    }

    public abstract void create(@NonNull O input);

    public void create(List<O> inputList) {
        ActiveAndroid.beginTransaction();
        try {
            for (O item : inputList) {
                create(item);
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    public List<O> getAll() {
        final List<I> entities = new Select()
                .from(claz)
                .execute();
        return dataMapper.transform(entities);
    }

    public O get(@NonNull String column, final String value) {
        final I entity = new Select()
                .from(claz)
                .where(eq(column, value))
                .executeSingle();
        return dataMapper.transform(entity);
    }

}
