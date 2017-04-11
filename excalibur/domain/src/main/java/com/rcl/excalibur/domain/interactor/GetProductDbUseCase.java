package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.ProductRepository;

import java.util.List;

public class GetProductDbUseCase extends UseCaseSync<ProductRepository> {

    public GetProductDbUseCase(ProductRepository productRepository) {
        super(productRepository);
    }

    public List<Product> getAll() {
        return getData().getAll();
    }

    public Product get(long productId) {
        return getData().get(productId);
    }

    public List<Product> getAll(String type) {
        return getData().getAll(type);
    }
}
