package com.rcl.excalibur.data.utils;


import com.rcl.excalibur.data.service.response.BaseResponse;

public final class ServiceUtil {

    private static final String SUCCESS = "SUCCESS";

    private ServiceUtil() {
    }

    public static boolean isSuccess(BaseResponse baseResponse) {
        return baseResponse != null && SUCCESS.equals(baseResponse.getResponseStatus());
    }
}
