package com.rcl.excalibur.domain;

import java.util.Calendar;
import java.util.Date;

public class Offering implements Comparable<Offering> {
    private static final int MORNING_START_HOUR = 6;

    private String id;
    private Date date;
    private String dateString;
    private String timeString;
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

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    @Override
    public int compareTo(Offering input) {
        Calendar calendarActual = Calendar.getInstance();
        calendarActual.setTimeInMillis(getDate().getTime());

        Calendar calendarInput = Calendar.getInstance();
        calendarInput.setTimeInMillis(input.getDate().getTime());

        int result = calendarActual.compareTo(calendarInput);
        if (result == 0) {
            result = getProduct().getProductTitle().compareToIgnoreCase(input.getProduct().getProductTitle());
        } else if (calendarActual.get(Calendar.HOUR_OF_DAY) >= MORNING_START_HOUR
                && calendarInput.get(Calendar.HOUR_OF_DAY) < MORNING_START_HOUR) {
            result = -1;
        } else if (calendarActual.get(Calendar.HOUR_OF_DAY) < MORNING_START_HOUR
                && calendarInput.get(Calendar.HOUR_OF_DAY) >= MORNING_START_HOUR) {
            result = 1;
        }
        return result;
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
