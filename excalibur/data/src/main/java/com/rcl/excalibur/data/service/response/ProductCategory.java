
package com.rcl.excalibur.data.service.response;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ProductCategory {

    private String categoryDescription;
    @SerializedName("categoryid")
    private String categoryId;
    private List<ProductTagResponse> productTags;

    public ProductCategory() {
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductTagResponse> getProductTags() {
        return productTags;
    }

    public void setProductTags(List<ProductTagResponse> productTags) {
        this.productTags = productTags;
    }
}
