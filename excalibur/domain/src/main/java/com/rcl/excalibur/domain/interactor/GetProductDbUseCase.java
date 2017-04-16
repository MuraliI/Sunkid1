package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.ProductRepository;

import java.util.List;

import io.reactivex.Observer;

public class GetProductDbUseCase extends UseCaseSync<ProductRepository> {

    public GetProductDbUseCase(ProductRepository productRepository) {
        super(productRepository);
    }

    public void getAll(Observer<List<Product>> observer) {
        getData().getAll(observer);
    }

    public Product get(String productId) {
        return getData().get(productId);
    }

    public void getAll(String type, Observer<List<Product>> observer) {
        getData().getAll(type, observer);
    }
}
