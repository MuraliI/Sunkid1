package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.mapper.BaseDataMapper;

import java.util.List;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

public abstract class BaseDataRepository<I, E extends Model> {

    private final BaseDataMapper<I, E> dataMapper;
    private final Class<E> claz;

    protected BaseDataRepository(BaseDataMapper dataMapper, Class<E> claz) {
        this.dataMapper = dataMapper;
        this.claz = claz;
    }

    protected BaseDataMapper<I, E> getMapper() {
        return dataMapper;
    }

    public abstract void create(@NonNull I value);

    public List<I> getAll() {
        final List<E> entities = new Select()
                .from(claz)
                .execute();
        return dataMapper.transform(entities);
    }

    public I get(@NonNull String column, final String value) {
        final E entity = new Select()
                .from(claz)
                .where(eq(column, value))
                .executeSingle();
        return dataMapper.transform(entity);
    }

    public I get() {
        final E entity = new Select()
                .from(claz)
                .executeSingle();
        return dataMapper.transform(entity);
    }

}
