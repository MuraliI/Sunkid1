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

    public Product get(String productId) {
        return getData().get(productId);
    }

    public List<Product> getAll(String type) {
        return getData().getAll(type);
    }

    public List<Product> getByCategory(String category, int maxCount, int offset) {
        return getData().getByCategory(category, maxCount, offset);
    }

    public List<Product> getByChildCategory(String childCategory, int maxCount, int offset) {
        return getData().getByChildCategory(childCategory, maxCount, offset);
    }
}
