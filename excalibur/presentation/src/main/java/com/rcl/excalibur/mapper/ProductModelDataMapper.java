package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductAdvisement;
import com.rcl.excalibur.domain.ProductRestriction;
import com.rcl.excalibur.model.ProductModel;

import java.util.List;

public class ProductModelDataMapper extends BaseModelDataMapper<Product, ProductModel> {

    @NonNull
    @Override
    public ProductModel transform(Product item) {
        if (item == null) {
            return null;
        }
        final ProductModel productModel = new ProductModel();
        productModel.setDuration(item.getProductDuration().getDurationInMinutes());
        productModel.setDescription(item.getProductShortDescription());
        setAdvisements(productModel, item.getAdvisements());
        setRestrictions(productModel, item.getRestrictions());
        return productModel;
    }

    private void setAdvisements(ProductModel product, List<ProductAdvisement> advisements) {
        int size = advisements.size();
        if (advisements.size() == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            String type = advisements.get(i).getAdvisementType();
            String description = advisements.get(i).getAdvisementDescription();
            product.getAdvisementsAndReestrictions().put(type, description);
        }
    }

    private void setRestrictions(ProductModel product, List<ProductRestriction> restrictions) {
        int size = restrictions.size();
        if (restrictions.size() == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            String type = restrictions.get(i).getRestrictionType();
            String description = restrictions.get(i).getRestrictionDescription();
            product.getAdvisementsAndReestrictions().put(type, description);
        }
    }
}

