package com.rcl.excalibur.data.service.response;

import java.util.List;

public class GetSpasResponse extends BaseResponse {

    private List<ProductResponse> product;

    public GetSpasResponse() {
    }

    public List<ProductResponse> getProduct() {
        return product;
    }

    public void setProduct(List<ProductResponse> product) {
        this.product = product;
    }
}
