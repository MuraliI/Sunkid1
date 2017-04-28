package com.rcl.excalibur.data.utils;


import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.data.service.api.DiscoverApi;
import com.rcl.excalibur.data.service.api.GuestApi;
import com.rcl.excalibur.data.service.api.MenuApi;
import com.rcl.excalibur.data.service.api.SailDateApi;
import com.rcl.excalibur.data.service.api.ShipTimeApi;
import com.rcl.excalibur.data.service.response.BaseResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

public final class ServiceUtil {

    public static final String APIKEY = "CYNbcRaszWgPArZBHA4Wz4Jv2wK20J09";
    private static final String SUCCESS = "SUCCESS";
    private static DiscoverApi discoverApi;
    private static GuestApi guestApi;
    private static SailDateApi sailDateApi;
    private static ShipTimeApi shipTimeApi;
    private static MenuApi menuApi;


    private ServiceUtil() {
    }

    public static boolean isSuccess(BaseResponse baseResponse) {
        return baseResponse != null && SUCCESS.equals(baseResponse.getResponseStatus());
    }

    public static ShipTimeApi getShipTimeApi() {
        if (shipTimeApi == null) {
            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(BuildConfig.SHIP_TIME_API_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    client(getClient())
                    .build();
            shipTimeApi = retrofit.create(ShipTimeApi.class);
        }
        return shipTimeApi;
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

    public static MenuApi getMenuApi() {
        if (menuApi == null) {
            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(BuildConfig.MENUS_API_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    client(getClient())
                    .build();
            menuApi = retrofit.create(MenuApi.class);
        }

        return menuApi;
    }

    public static SailDateApi getSailDateApi() {
        if (sailDateApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.GUEST_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
            sailDateApi = retrofit.create(SailDateApi.class);
        }
        return sailDateApi;
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
