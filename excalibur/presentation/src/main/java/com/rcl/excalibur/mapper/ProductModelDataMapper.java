package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.model.ProductModel;

public class ProductModelDataMapper extends BaseModelDataMapper<Product, ProductModel> {

    @NonNull
    @Override
    public ProductModel transform(Product item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final ProductModel productModel = new ProductModel();
        productModel.setDuration(item.getProductDuration().getDurationInMinutes());

        return productModel;
    }

}

