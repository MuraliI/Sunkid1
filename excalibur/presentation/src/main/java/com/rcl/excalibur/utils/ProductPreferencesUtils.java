package com.rcl.excalibur.utils;

import com.rcl.excalibur.domain.ProductPreference;

import java.util.ArrayList;
import java.util.List;

public class ProductPreferencesUtils {

    private List<ProductPreference> properties;

    public ProductPreferencesUtils() {
        properties = new ArrayList<>();
    }

    public void addProduct(String title, String instructions) {
        ProductPreference productPreference = new ProductPreference();
        productPreference.setPreferenceName(title);
        productPreference.setPreferenceType(instructions);

        getProperties().add(productPreference);
    }


    public List<ProductPreference> getProperties() {
        return properties;
    }

}
