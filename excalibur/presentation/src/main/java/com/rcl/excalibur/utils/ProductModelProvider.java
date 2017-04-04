package com.rcl.excalibur.utils;

import com.rcl.excalibur.model.ProductAccessibilityModel;
import com.rcl.excalibur.model.ProductModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProductModelProvider {
    public static Map<String, ProductModel> productModelMap;
    public static List<ProductAccessibilityModel> accessibilitiesList;

    static {
        productModelMap = new HashMap<>();
        accessibilitiesList = new ArrayList<>();
        ProductModel productModel = new ProductModel();
        productModel.setProductId("1");
        productModel.getAdvisementsAndReestrictions().put("Cuisine", "Colombian");
        productModelMap.put("1", productModel);
        //Add accesibilities
        ProductAccessibilityModel accessibility = new ProductAccessibilityModel();
        accessibility.setDescription("");
        accessibility.setSubtitle("TestSubtitle:");
        accessibility.setImageUrl("TestUrl");

        ProductAccessibilityModel accessibility1 = new ProductAccessibilityModel();
        accessibility1.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
                + "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley"
                + " of type and s");
        accessibility1.setSubtitle("Test1Subtitle");
        accessibility1.setImageUrl("Test1Url");

        accessibilitiesList.add(accessibility);
        accessibilitiesList.add(accessibility1);
    }

    private ProductModelProvider() {
    }
}
