package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.ProductRepository;

import java.util.List;

public class GetProductDbUseCase extends UseCaseSync {

    private final ProductRepository productRepository;

    public GetProductDbUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Product get(String productId) {
        return productRepository.get(productId);
    }

    public List<Product> getAll(String type) {
        return productRepository.getAll(type);
    }
}
