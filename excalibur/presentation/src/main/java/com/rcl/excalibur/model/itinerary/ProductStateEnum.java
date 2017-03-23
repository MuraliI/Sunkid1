package com.rcl.excalibur.model.itinerary;

public enum ProductStateEnum {

    //Value represent priority in list Higher priority go on top
    PAST(3), ON_GOING(2), UP_COMING(1);

    int value;

    ProductStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
