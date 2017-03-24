package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.service.ProductService;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


public class GetProductsUseCase extends UseCase<List<Product>, Void> {

    private final ProductService productService;

    public GetProductsUseCase(ProductService productService) {
        super();
        this.productService = productService;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<Product>> observer, Void aVoid) {
        productService.getProducts();
    }

}
