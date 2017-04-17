package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Product;

import java.util.List;

import io.reactivex.Observer;

public interface ProductRepository {

    void create(Product product);

    void create(List<Product> product);

    void getAll(Observer<List<Product>> observer);

    void getAll(String type, Observer<List<Product>> observer);

    Product get(String id);

    void deleteAll();
}
