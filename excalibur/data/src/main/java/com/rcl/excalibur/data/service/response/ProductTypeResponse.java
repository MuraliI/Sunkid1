package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

public class ProductTypeResponse {
    @SerializedName("productTypeID")
    private long productTypeId;
    private String productTypeName;
    private String productType;

    public long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(long productTypeId) {
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
