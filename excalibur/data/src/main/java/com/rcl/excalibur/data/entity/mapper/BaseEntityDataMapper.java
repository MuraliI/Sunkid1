package com.rcl.excalibur.data.entity.mapper;


import com.activeandroid.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class BaseEntityDataMapper<I, E extends Model> {

    public abstract I transform(E entity);

    public List<I> transform(Collection<E> entityCollection) {
        final List<I> list = new ArrayList();
        for (E entity : entityCollection) {
            final I item = transform(entity);
            if (item != null) {
                list.add(item);
            }
        }
        return list;
    }
}
