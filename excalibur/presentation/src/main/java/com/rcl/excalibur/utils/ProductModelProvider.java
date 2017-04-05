package com.rcl.excalibur.utils;

import com.rcl.excalibur.model.ProductModel;

import java.util.HashMap;
import java.util.Map;

public final class ProductModelProvider {
    public static Map<String, ProductModel> productModelMap;

    static {
        productModelMap = new HashMap<>();
        ProductModel productModel = new ProductModel();
        productModel.setProductId(1L);
        productModel.getAdvisements().put("Cuisine", "Colombian");
        productModelMap.put("1", productModel);
    }

    private ProductModelProvider() {
    }
}
