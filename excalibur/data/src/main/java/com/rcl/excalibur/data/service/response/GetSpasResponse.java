
package com.rcl.excalibur.data.service.response;

import java.util.List;

public class GetSpasResponse {

    private List<ProductResponse> product;
    private String responseStatus;

    public GetSpasResponse() {
    }


    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public List<ProductResponse> getProduct() {
        return product;
    }

    public void setProduct(List<ProductResponse> product) {
        this.product = product;
    }
}
