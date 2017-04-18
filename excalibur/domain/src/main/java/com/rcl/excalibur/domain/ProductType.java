package com.rcl.excalibur.domain;


public class ProductType {

    public static final String SHOREX_TYPE = "SHOREX";
    public static final String ACTIVITIES_TYPE = "ACTIVITIES";
    public static final String ENTERTAINMENT_TYPE = "ENTERTAINMENT";
    public static final String DINING_TYPE = "DINING";
    public static final String SPA_TYPE = "SPA";
    public static final String SHOPPING_TYPE = "SHOPPING";
    public static final String GUEST_SERVICES_TYPE = "GUEST_SERVICES";

    private String productTypeId;
    private String productTypeName;
    private String productType;

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
