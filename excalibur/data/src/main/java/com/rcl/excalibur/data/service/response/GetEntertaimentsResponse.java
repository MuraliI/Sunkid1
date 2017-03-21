package com.rcl.excalibur.data.service.response;

import java.util.List;


public class GetEntertaimentsResponse {

    private List<ProductResponse> products;
    private String responseStatus;

    public GetEntertaimentsResponse() {
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
