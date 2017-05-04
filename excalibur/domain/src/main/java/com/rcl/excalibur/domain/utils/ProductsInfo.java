package com.rcl.excalibur.domain.utils;


import com.rcl.excalibur.domain.Product;

import java.util.List;

public class ProductsInfo {
    private final String type;
    private final int totalHints;
    private final List<Product> products;

    public ProductsInfo(String type, int totalHints, List<Product> products) {
        this.type = type;
        this.totalHints = totalHints;
        this.products = products;
    }

    public ProductsInfo(String type) {
        this.type = type;
        this.totalHints = 0;
        this.products = null;
    }

    public String getType() {
        return type;
    }

    public int getTotalHints() {
        return totalHints;
    }

    public List<Product> getProducts() {
        return products;
    }
}
