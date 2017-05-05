package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.domain.Product;

import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_DINING_TIMES;

public class DiningTimesViewType implements RecyclerViewType {

    private String title;
    private Product product;

    public DiningTimesViewType(Product product, String title) {
        this.product = product;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int getViewType() {
        return VIEW_TYPE_DINING_TIMES;
    }
}
