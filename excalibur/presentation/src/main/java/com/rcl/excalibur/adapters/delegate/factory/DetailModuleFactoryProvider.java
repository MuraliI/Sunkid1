package com.rcl.excalibur.adapters.delegate.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DetailModuleFactoryProvider {
    private static final String TYPE_DINING = "DINING";
    private static final String TYPE_SHOREX = "SHOREX";
    private static final String TYPE_ACTIVITY = "ACTIVITIES";
    private static final String TYPE_ENTERTAINMENT = "ENTERTAINMENT";
    public static final String TYPE_SHOPPING = "Shopping"; // It doesn't exist in the service definition
    private static final String TYPE_SPA = "SPA";
    private static final String TYPE_BEVERAGES = "BEVERAGES";
    private static final String TYPE_INTERNET_PACKAGES = "INTERNET_PACKAGES";

    private Map<String, DetailModuleFactory> factoriesMap = new HashMap<>();

    public DetailModuleFactoryProvider() {
        factoriesMap.put(TYPE_DINING, new DinningDetailModuleFactory());
        factoriesMap.put(TYPE_ACTIVITY, new ActivityDetailModuleFactory());
        factoriesMap.put(TYPE_ENTERTAINMENT, new EntertainmentDetailModuleFactory());
        factoriesMap.put(TYPE_SHOREX, new ShorexDetailModuleFactory());
        factoriesMap.put(TYPE_SHOPPING, new ShoppingDetailModuleFactory());
        factoriesMap.put(TYPE_SPA, new SpaDetailModuleFactory());
    }

    @Nullable
    public DetailModuleFactory getFactory(@NonNull String type) {
        return factoriesMap.get(type);
    }
}
