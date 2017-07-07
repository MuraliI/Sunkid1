package com.rcl.excalibur.mapper;


import android.support.annotation.NonNull;

import com.rcl.excalibur.data.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseModelDataMapper<I, IM> {

    @NonNull
    public abstract IM transform(I item);

    @NonNull
    public Collection<IM> transform(Collection<I> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        Collection<IM> itemModelsCollection = new ArrayList<>();
        for (I item : collection) {
            itemModelsCollection.add(transform(item));
        }
        return itemModelsCollection;
    }
}
