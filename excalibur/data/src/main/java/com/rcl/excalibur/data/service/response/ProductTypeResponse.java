package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

public class ProductTypeResponse {
    @SerializedName("preferenceID")
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
