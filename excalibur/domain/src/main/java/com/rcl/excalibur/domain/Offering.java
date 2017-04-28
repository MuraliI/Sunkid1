package com.rcl.excalibur.domain;

import java.util.Calendar;
import java.util.Date;

public class Offering implements Comparable<Offering> {
    private static final int MORNING_START_HOUR = 6;

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
    public int compareTo(Offering input) {
        Calendar calendarActual = Calendar.getInstance();
        calendarActual.setTimeInMillis(getCompleteDate().getTime());

        Calendar calendarInput = Calendar.getInstance();
        calendarInput.setTimeInMillis(input.getCompleteDate().getTime());

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

    /*@Override
    public int compareTo(Offering input) {
        int result = (int) (this.getCompleteDate().getTime() - input.getCompleteDate().getTime());
        if (result == 0) {
            result = this.getProduct().getProductTitle()
                    .compareToIgnoreCase(input.getProduct().getProductTitle());
        } else {
            Calendar calendarActual = Calendar.getInstance();
            calendarActual.setTimeInMillis(this.getCompleteDate().getTime());

            Calendar calendarInput = Calendar.getInstance();
            calendarInput.setTimeInMillis(input.getCompleteDate().getTime());

            if (calendarActual.get(Calendar.HOUR) >= MORNING_START_HOUR
                    && calendarInput.get(Calendar.HOUR) < MORNING_START_HOUR) {
                return -1;
            }

            if (calendarActual.get(Calendar.HOUR) < MORNING_START_HOUR
                    && calendarInput.get(Calendar.HOUR) >= MORNING_START_HOUR) {
                return 1;
            }

            if (this.getCompleteDate().getTime() > input.getCompleteDate().getTime()) {
                result = 1;
            } else if (this.getCompleteDate().getTime() < input.getCompleteDate().getTime()) {
                result = -1;
            }
        }
        return result;
    }*/

    /*@Override
    public int compareTo(Offering input) {
        if (input == null) {
            return 0;
        } else if (this.getCompleteDate().getTime() > input.getCompleteDate().getTime()) {
            return 1;
        } else if (this.getCompleteDate().getTime() < input.getCompleteDate().getTime()) {
            return -1;
        } else {
            return 0;
        }
    }*/

    /*@Override
    public int compareTo(Offering input) {
        int result = getCompleteDate().compareTo(input.getCompleteDate());
        if (result == 0) {
            result = getProduct().getProductTitle()
                    .compareToIgnoreCase(input.getProduct().getProductTitle());
        }
        return result;
    }*/

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
