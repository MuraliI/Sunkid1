package com.rcl.excalibur.adapters.delegate.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DetailModuleFactoryProvider {
    private static final String TYPE_DINING = "Dining";
    private static final String TYPE_SHOREX = "Shorex";
    private static final String TYPE_ACTIVITY = "Activity";
    private static final String TYPE_ENTERTAINMENT = "Entertainment";
    private static final String TYPE_SPA = "Spa";

    private Map<String, DetailModuleFactory> factoriesMap = new HashMap<>();

    public DetailModuleFactoryProvider() {
        factoriesMap.put(TYPE_DINING, new DinningDetailModuleFactory());
        factoriesMap.put(TYPE_ACTIVITY, new ActivityDetailModuleFactory());
        factoriesMap.put(TYPE_ENTERTAINMENT, new EntertainmentDetailModuleFactory());
        factoriesMap.put(TYPE_SPA, new SpaDetailModuleFactory());
    }

    @Nullable
    public DetailModuleFactory getFactory(@NonNull String type) {
        return factoriesMap.get(type);
    }
}
