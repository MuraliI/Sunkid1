package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Product;

import java.util.List;

public interface ProductRepository {

    void create(Product product);

    void create(List<Product> product);

    List<Product> getAll();

    List<Product> getAll(String type);

    List<Product> getByType(String type, int maxCount, int offset);

    Product get(String id);

    void deleteAll();
}
