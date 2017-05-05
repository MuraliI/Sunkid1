package com.rcl.excalibur.data.utils;


import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.SubCategory;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.domain.repository.SubCategoryRepository;
import com.rcl.excalibur.domain.service.DiscoverServices;
import com.rcl.excalibur.domain.service.SubCategoryServices;
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
    private static List<DisposableObserver<Boolean>> productsObservers = new ArrayList<>();
    private static List<DisposableObserver<Boolean>> subcategoriesObservers = new ArrayList<>();
    private DiscoverServices discoverServices;
    private ProductRepository productRepository;
    private SubCategoryServices subCategoryServices;
    private SubCategoryRepository subCategoryRepository;

    private String sailingId;
    private List<TypeOffset> pairs;
    private int actualPositionType;

    public DownloadProductsManager(String sailingId, DiscoverServices discoverServices, ProductRepository productRepository
            , SubCategoryServices subCategoryServices, SubCategoryRepository subCategoryRepository) {
        this.discoverServices = discoverServices;
        this.sailingId = sailingId;
        this.productRepository = productRepository;
        this.subCategoryServices = subCategoryServices;
        this.subCategoryRepository = subCategoryRepository;
        init();
    }

    public static void addProductsObserver(DisposableObserver<Boolean> observer) {
        productsObservers.add(observer);
    }

    public static void removeProductsObserver(DisposableObserver<Boolean> observer) {
        productsObservers.remove(observer);
    }

    public static void addSubcategoriesObserver(DisposableObserver<Boolean> observer) {
        subcategoriesObservers.add(observer);
    }

    public static void removeSubcategoriesObserver(DisposableObserver<Boolean> observer) {
        subcategoriesObservers.remove(observer);
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
        final TypeOffset pair = next();
        if (pair == null) {
            notifyObservers(productsObservers);
            retrieveSubCategories();
            return;
        }
        retrieveProducts(pair.type, pair.offset);
        pair.increment(MAX_ELEMENT);
    }

    private void notifyObservers(List<DisposableObserver<Boolean>> observers) {
        for (DisposableObserver observer : observers) {
            observer.onNext(true);
        }
    }

    private void retrieveProducts(@NonNull String type, int offset) {
        discoverServices.getProducts(new ProductObserver(), sailingId, type, MAX_ELEMENT, offset);
    }

    private void retrieveSubCategories() {
        subCategoryServices.getSubCategories(new SubCategoriesObserver(), sailingId);
    }

    private void createOrUpdateProducts(final List<Product> products) {
        if (!CollectionUtils.isEmpty(products)) {
            productRepository.create(products);
        }
        process();
    }

    private void createOrUpdateSubCategories(final List<SubCategory> subCategories) {
        if (CollectionUtils.isEmpty(subCategories)) {
            return;
        }
        subCategoryRepository.create(subCategories);
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


    private class SubCategoriesObserver extends DisposableObserver<List<SubCategory>> {

        @Override
        public void onNext(List<SubCategory> subCategories) {
            createOrUpdateSubCategories(subCategories);
            notifyObservers(subcategoriesObservers);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

}
