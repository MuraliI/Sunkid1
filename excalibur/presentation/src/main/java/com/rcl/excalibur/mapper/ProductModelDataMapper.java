package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.model.ProductModel;

public class ProductModelDataMapper extends BaseModelDataMapper<Product, ProductModel> {

    @NonNull
    @Override
    public ProductModel transform(Product item) {
        if (item == null) {
            return null;
        }
        final ProductModel productModel = new ProductModel();
        productModel.setDuration(item.getProductDuration().getDurationInMinutes());
        productModel.setReservationInformation(item.getProductReservationInformation());

        return productModel;
    }

}

