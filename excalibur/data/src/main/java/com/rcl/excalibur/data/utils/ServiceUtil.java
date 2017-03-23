package com.rcl.excalibur.data.utils;


import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.data.service.api.DiscoverApi;
import com.rcl.excalibur.data.service.response.BaseResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ServiceUtil {

    private static final String SUCCESS = "SUCCESS";

    private ServiceUtil() {
    }

    public static DiscoverApi getDiscoverApi() {
        final OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(BuildConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        final Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BuildConfig.DISCOVER_API_URL).
                addConverterFactory(GsonConverterFactory.create()).
                client(client)
                .build();
        return retrofit.create(DiscoverApi.class);
    }

    public static boolean isSuccess(BaseResponse baseResponse) {
        return baseResponse != null && SUCCESS.equals(baseResponse.getResponseStatus());
    }
}
