package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

public class GetProductDbUseCase extends UseCaseSync {

    private final ProductRepository productRepository;

    @Inject
    GetProductDbUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Product get(long productId) {
        return productRepository.get(productId);
    }

    public List<Product> getAll(String type) {
        return productRepository.getAll(type);
    }
}
