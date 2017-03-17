
package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

public class ProductTypeResponse {

    private String productType;
    @SerializedName("productTypeId")
    private String productTypeId;
    private String productTypeName;

    public ProductTypeResponse() {
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

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
}
