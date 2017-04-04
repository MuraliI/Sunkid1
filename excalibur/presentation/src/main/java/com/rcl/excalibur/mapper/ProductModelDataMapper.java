package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductAdvisement;
import com.rcl.excalibur.domain.ProductRestriction;
import com.rcl.excalibur.model.ProductAccessibilityModel;
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
            if (type.equals(ProductAdvisement.ACCESSIBILITY)) {
                addAccesibility(product, advisements.get(i));
            } else {
                product.getAdvisementsAndReestrictions().put(type, description);
            }
        }
    }

    private void addAccesibility(ProductModel product, ProductAdvisement advisement) {
        ProductAccessibilityModel accessibility = new ProductAccessibilityModel();
        // TODO: choose the correct Media Type according the situation
        accessibility.setImageUrl(advisement.getAdvisementMedia().getMediaItem().get(0).getMediaRefLink());
        accessibility.setSubtitle(advisement.getAdvisementTitle());
        accessibility.setDescription(advisement.getAdvisementDescription());
        product.getAccessibilities().add(accessibility);
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

