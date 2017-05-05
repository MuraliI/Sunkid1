package com.rcl.excalibur.data.utils;


import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.CategoryRepository;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.domain.service.CategoryServices;
import com.rcl.excalibur.domain.service.DiscoverServices;
import com.rcl.excalibur.domain.utils.ProductsInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static com.rcl.excalibur.domain.Category.ACTIVITIES_CATEGORY;
import static com.rcl.excalibur.domain.Category.DINING_CATEGORY;
import static com.rcl.excalibur.domain.Category.ENTERTAINMENT_CATEGORY;
import static com.rcl.excalibur.domain.Category.GUEST_SERVICES_CATEGORY;
import static com.rcl.excalibur.domain.Category.SHOPPING_CATEGORY;
import static com.rcl.excalibur.domain.Category.SHOREX_CATEGORY;
import static com.rcl.excalibur.domain.Category.SPA_CATEGORY;

public class DownloadProductsManager {
    private static final int MAX_ELEMENT = 50;
    private static List<DisposableObserver<Boolean>> observers = new ArrayList<>();
    private DiscoverServices discoverServices;
    private ProductRepository productRepository;
    private CategoryServices categoryServices;
    private CategoryRepository categoryRepository;

    private String sailingId;
    private List<TypeOffset> pairs;
    private int actualPositionType;

    public DownloadProductsManager(String sailingId, DiscoverServices discoverServices, ProductRepository productRepository
            , CategoryServices categoryServices, CategoryRepository categoryRepository) {
        this.discoverServices = discoverServices;
        this.sailingId = sailingId;
        this.productRepository = productRepository;
        this.categoryServices = categoryServices;
        this.categoryRepository = categoryRepository;
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
        pairs.add(new TypeOffset(SHOREX_CATEGORY, 0));
        pairs.add(new TypeOffset(ACTIVITIES_CATEGORY, 0));
        pairs.add(new TypeOffset(ENTERTAINMENT_CATEGORY, 0));
        pairs.add(new TypeOffset(DINING_CATEGORY, 0));
        pairs.add(new TypeOffset(SPA_CATEGORY, 0));
        pairs.add(new TypeOffset(SHOPPING_CATEGORY, 0));
        pairs.add(new TypeOffset(GUEST_SERVICES_CATEGORY, 0));
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
        retrieveCategories();
    }

    private void innerProcess() {
        final TypeOffset pair = next();
        if (pair == null) {
            notifyObservers();
            return;
        }
        retrieveProducts(pair.type, pair.offset);
        pair.increment(MAX_ELEMENT);
    }

    private void notifyObservers() {
        for (DisposableObserver observer : observers) {
            observer.onNext(true);
        }
    }

    private void retrieveProducts(@NonNull String type, int offset) {
        discoverServices.getProducts(new ProductObserver(), sailingId, type, MAX_ELEMENT, offset);
    }

    private void retrieveCategories() {
        categoryServices.getCategories(new CategoriesObserver(), sailingId);
    }

    private void createOrUpdateProducts(final List<Product> products) {
        if (!CollectionUtils.isEmpty(products)) {
            productRepository.create(products);
        }
        innerProcess();
    }

    private void createOrUpdateCategories(final List<Category> categories) {
        if (CollectionUtils.isEmpty(categories)) {
            return;
        }
        categoryRepository.create(categories);
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

    private class ProductObserver extends DisposableObserver<ProductsInfo> {

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
            createOrUpdateProducts(products);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }


    private class CategoriesObserver extends DisposableObserver<List<Category>> {

        @Override
        public void onNext(List<Category> categories) {
            createOrUpdateCategories(categories);
            innerProcess();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

}
