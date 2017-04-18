package com.rcl.excalibur.domain;

import java.util.Date;

public class Offering implements Comparable<Offering> {
    private String id;
    private Date date;
    private SellingPrice price;
    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int compareTo(Offering o) {
        if (this.getDate().getTime() > o.getDate().getTime()) {
            return 1;
        } else if (this.getDate().getTime() < o.getDate().getTime()) {
            return -1;
        } else {
            return 0;
        }
    }

    public int compareByPrice(Offering o2) {

        if (o2 == null) {
            return 0;
        }

        if (this.getPrice() == null || o2.getPrice() == null) {
            return 0;
        }

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
