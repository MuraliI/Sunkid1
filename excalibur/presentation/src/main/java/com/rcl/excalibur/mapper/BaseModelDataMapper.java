package com.rcl.excalibur.mapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseModelDataMapper<IM, I> {

    public abstract IM transform(I item);

    public Collection<IM> transform(Collection<I> collection) {
        Collection<IM> itemModelsCollection;

        if (collection != null && !collection.isEmpty()) {
            itemModelsCollection = new ArrayList<>();
            for (I item : collection) {
                itemModelsCollection.add(transform(item));
            }
        } else {
            itemModelsCollection = Collections.emptyList();
        }

        return itemModelsCollection;
    }
}
