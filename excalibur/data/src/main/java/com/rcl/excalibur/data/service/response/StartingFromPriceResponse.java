
package com.rcl.excalibur.data.service.response;

public class StartingFromPriceResponse {

    private String currency;
    private Long adultPrice;
    private Long childPrice;
    private Long infantPrice;

    public StartingFromPriceResponse() {
    }

    public Long getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(Long adultPrice) {
        this.adultPrice = adultPrice;
    }

    public Long getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(Long childPrice) {
        this.childPrice = childPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(Long infantPrice) {
        this.infantPrice = infantPrice;
    }

}
