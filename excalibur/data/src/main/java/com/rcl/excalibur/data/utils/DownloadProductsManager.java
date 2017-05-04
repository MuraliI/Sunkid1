package com.rcl.excalibur.data.utils;


import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.domain.service.DiscoverServices;
import com.rcl.excalibur.domain.utils.ProductsInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static com.rcl.excalibur.domain.ProductType.ACTIVITIES_TYPE;
import static com.rcl.excalibur.domain.ProductType.DINING_TYPE;
import static com.rcl.excalibur.domain.ProductType.ENTERTAINMENT_TYPE;
import static com.rcl.excalibur.domain.ProductType.GUEST_SERVICES_TYPE;
import static com.rcl.excalibur.domain.ProductType.SHOPPING_TYPE;
import static com.rcl.excalibur.domain.ProductType.SHOREX_TYPE;
import static com.rcl.excalibur.domain.ProductType.SPA_TYPE;

public class DownloadProductsManager {
    private static final int MAX_ELEMENT = 50;
    private static List<DisposableObserver<Boolean>> observers = new ArrayList<>();
    private DiscoverServices discoverServices;
    private ProductRepository productRepository;
    private String sailingId;
    private List<TypeOffset> pairs;
    private int actualPositionType;

    public DownloadProductsManager(String sailingId, DiscoverServices discoverServices, ProductRepository productRepository) {
        this.discoverServices = discoverServices;
        this.sailingId = sailingId;
        this.productRepository = productRepository;
        init();
    }

    public static void addObserver(DisposableObserver<Boolean> observer) {
        observers.add(observer);
    }

    public static void removeObserver(DisposableObserver<Boolean> observer) {
        observers.remove(observer);
    }

    private void init() {
        actualPositionType = -1;
        pairs = new ArrayList<>();
        pairs.add(new TypeOffset(SHOREX_TYPE, 0));
        pairs.add(new TypeOffset(ACTIVITIES_TYPE, 0));
        pairs.add(new TypeOffset(ENTERTAINMENT_TYPE, 0));
        pairs.add(new TypeOffset(DINING_TYPE, 0));
        pairs.add(new TypeOffset(SPA_TYPE, 0));
        pairs.add(new TypeOffset(SHOPPING_TYPE, 0));
        pairs.add(new TypeOffset(GUEST_SERVICES_TYPE, 0));
    }

    private TypeOffset next() {
        if (pairs.isEmpty()) {
            return null;
        }
        actualPositionType++;
        if (actualPositionType >= pairs.size()) {
            actualPositionType = 0;
        }
        return pairs.get(actualPositionType);
    }

    public void process() {
        final TypeOffset pair = next();
        if (pair == null) {
            notifyObservers();
            return;
        }
        retrieveElements(pair.type, pair.offset);
        pair.increment(MAX_ELEMENT);
    }

    private void notifyObservers() {
        for (DisposableObserver observer : observers) {
            observer.onNext(true);
        }
    }

    private void retrieveElements(@NonNull String type, int offset) {
        discoverServices.getProducts(new Observer(), sailingId, type, MAX_ELEMENT, offset);
    }

    private void createOrUpdate(final List<Product> products) {
        if (!CollectionUtils.isEmpty(products)) {
            long init = System.currentTimeMillis();
            productRepository.create(products);
            long fin = (System.currentTimeMillis() - init);
        }
        process();
    }

    private void removeType(String type) {
        for (TypeOffset typeOffset : pairs) {
            if (typeOffset.type.equals(type)) {
                pairs.remove(typeOffset);
                return;
            }
        }
    }

    private static class TypeOffset {
        final String type;
        int offset;

        public TypeOffset(String type, int offset) {
            this.type = type;
            this.offset = offset;
        }

        public void increment(int value) {
            offset += value;
        }
    }

    private class Observer extends DisposableObserver<ProductsInfo> {

        @Override
        public void onNext(ProductsInfo value) {
            final List<Product> products = value.getProducts();
            if (CollectionUtils.isEmpty(products) || products.size() < MAX_ELEMENT) {
                removeType(value.getType());
            } else {
                TypeOffset typeOffset = pairs.get(actualPositionType);
                if (typeOffset != null && typeOffset.offset >= value.getTotalHints()) {
                    removeType(value.getType());
                }
            }
            createOrUpdate(products);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
