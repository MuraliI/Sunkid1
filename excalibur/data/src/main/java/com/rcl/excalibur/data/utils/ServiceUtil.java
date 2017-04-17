package com.rcl.excalibur.data.utils;


import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.data.service.api.DiscoverApi;
import com.rcl.excalibur.data.service.api.GuestApi;
import com.rcl.excalibur.data.service.response.BaseResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

public final class ServiceUtil {

    private static final String SUCCESS = "SUCCESS";
    private static DiscoverApi discoverApi;
    private static GuestApi guestApi;

    private ServiceUtil() {
    }

    public static boolean isSuccess(BaseResponse baseResponse) {
        return baseResponse != null && SUCCESS.equals(baseResponse.getResponseStatus());
    }

    public static DiscoverApi getDiscoverApi() {
        if (discoverApi == null) {
            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(BuildConfig.DISCOVER_API_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    client(getClient())
                    .build();
            discoverApi = retrofit.create(DiscoverApi.class);
        }
        return discoverApi;
    }

    public static GuestApi getGuestApi() {
        if (guestApi == null) {
            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(BuildConfig.GUEST_API_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    client(getClient())
                    .build();
            guestApi = retrofit.create(GuestApi.class);
        }
        return guestApi;
    }

    private static OkHttpClient getClient() {
        final OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(BuildConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            //show request log in debug mode
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BODY));
        }

        return builder.build();
    }


}
