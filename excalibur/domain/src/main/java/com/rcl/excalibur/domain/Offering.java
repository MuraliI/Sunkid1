package com.rcl.excalibur.domain;

import java.util.Date;

public class Offering implements Comparable<Offering> {
    private String id;
    private String date;
    private String time;
    private SellingPrice price;
    private Product product;
    private Date completeDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
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
        if (o == null) {
            return 0;
        } else if (this.getCompleteDate().getTime() > o.getCompleteDate().getTime()) {
            return 1;
        } else if (this.getCompleteDate().getTime() < o.getCompleteDate().getTime()) {
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
