package com.rcl.excalibur.data.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseDataMapper<I, E> {

    public abstract I transform(E entity);

    public List<I> transform(Collection<E> collection) {
        final List<I> list = new ArrayList();
        if (collection == null) {
            return list;
        }
        for (E element : collection) {
            final I item = transform(element);
            if (item != null) {
                list.add(item);
            }
        }
        return list;
    }
}
