package com.rcl.excalibur.data.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseDataMapper<O, I> {

    @Nullable
    public abstract O transform(I input);

    @NonNull
    public List<O> transform(@Nullable Collection<I> collection) {
        final List<O> list = new ArrayList<>();
        if (collection == null) {
            return list;
        }
        for (I element : collection) {
            final O item = transform(element);
            if (item != null) {
                list.add(item);
            }
        }
        return list;
    }
}
