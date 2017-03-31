package com.rcl.excalibur.model;

import java.util.LinkedHashMap;

public class ProductModel {
    //TODO Add all your attributes here
    private LinkedHashMap<String, String> advisements = new LinkedHashMap<>();
    private String productId;

    public LinkedHashMap<String, String> getAdvisements() {
        return advisements;
    }

    public void setAdvisements(LinkedHashMap<String, String> advisements) {
        this.advisements = advisements;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
