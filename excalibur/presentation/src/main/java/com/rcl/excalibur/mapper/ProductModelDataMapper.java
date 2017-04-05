package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.rcl.excalibur.data.utils.CollectionUtils;
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
        productModel.setReservationInformation(item.getProductReservationInformation());
        setAdvisements(productModel, item.getAdvisements());
        setRestrictions(productModel, item.getRestrictions());
        return productModel;
    }

    private void setAdvisements(ProductModel product, List<ProductAdvisement> advisements) {
        if (CollectionUtils.isEmpty(advisements)) {
            return;
        }
        for (int i = 0; i < advisements.size(); i++) {
            String type = advisements.get(i).getAdvisementType();
            String description = advisements.get(i).getAdvisementDescription();
            if (!TextUtils.isEmpty(type)) {
                if (type.equals(ProductAdvisement.ACCESSIBILITY)) {
                    addAccesibility(product, advisements.get(i));
                } else {
                    product.getAdvisementsAndReestrictions().put(type, description);
                }
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
        if (CollectionUtils.isEmpty(restrictions)) {
            return;
        }
        for (int i = 0; i < restrictions.size(); i++) {
            String type = restrictions.get(i).getRestrictionType();
            if (!TextUtils.isEmpty(type)) {
                String description = restrictions.get(i).getRestrictionDisplayText();
                product.getAdvisementsAndReestrictions().put(type, description);
            }
        }
    }
}

