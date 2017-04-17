package com.rcl.excalibur.domain;

import java.util.Date;

public class Offering {
    private String id;
    private Date date;
    private SellingPrice price;
    private String productId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SellingPrice getPrice() {
        return price;
    }

    public void setPrice(SellingPrice price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int compareByPrice(Offering o2) {
        if (this.getPrice().getAdultPrice() == o2.getPrice().getAdultPrice()) {
            if (this.getPrice().getChildPrice() < this.getPrice().getChildPrice()) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.getPrice().getAdultPrice() < o2.getPrice().getAdultPrice()) {
            return -1;
        } else {
            return 1;
        }
    }
}
