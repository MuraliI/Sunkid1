package com.rcl.excalibur.data.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseDataMapper<I, E> {

    @Nullable
    public abstract I transform(E entity);

    @NonNull
    public List<I> transform(@Nullable Collection<E> collection) {
        final List<I> list = new ArrayList<>();
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
