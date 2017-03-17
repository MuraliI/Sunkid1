
package com.rcl.excalibur.data.service.response;

import java.util.List;


public class CategoryResponse {

    private String categoryDescription;
    private long categoryid;
    private List<String> productTags;

    public CategoryResponse() {
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<String> getProductTags() {
        return productTags;
    }

    public void setProductTags(List<String> productTags) {
        this.productTags = productTags;
    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }
}
