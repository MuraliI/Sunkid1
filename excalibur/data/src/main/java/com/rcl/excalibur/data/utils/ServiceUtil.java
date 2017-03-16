package com.rcl.excalibur.data.utils;


import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.data.service.api.DiscoveryApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ServiceUtil {

    private ServiceUtil() {
    }

    public static DiscoveryApi getDiscoveryApi() {
        final OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(BuildConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        final Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BuildConfig.DISCOVERY_API_URL).
                addConverterFactory(GsonConverterFactory.create()).
                client(client)
                .build();
        return retrofit.create(DiscoveryApi.class);
    }
}
