package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.mapper.BaseDataMapper;

import java.util.List;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

public abstract class BaseDataRepository<I, E extends Model> {

    protected final BaseDataMapper<I, E> baseDataMapper;
    private final Class<E> claz;

    protected BaseDataRepository(BaseDataMapper baseDataMapper, Class<E> claz) {
        this.baseDataMapper = baseDataMapper;
        this.claz = claz;
    }

    public abstract void create(@NonNull I promotion);

    public List<I> getAll() {
        final List<E> entities = new Select()
                .from(claz)
                .execute();
        return baseDataMapper.transform(entities);
    }

    public I get(@NonNull String column, final String value) {
        final E entity = new Select()
                .from(claz)
                .where(eq(column, value))
                .executeSingle();
        return baseDataMapper.transform(entity);
    }

}
