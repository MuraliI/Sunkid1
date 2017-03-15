package com.rcl.excalibur.mapper;


import com.rcl.excalibur.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseModelDataMapper<IM, I> {

    public abstract IM transform(I item);

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
