package com.rcl.excalibur.data.utils;


import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.data.service.api.DiscoverApi;
import com.rcl.excalibur.data.service.api.GuestApi;
import com.rcl.excalibur.data.service.api.MenuApi;
import com.rcl.excalibur.data.service.api.MockableApi;
import com.rcl.excalibur.data.service.api.SailDateApi;
import com.rcl.excalibur.data.service.api.ShipStatsApi;
import com.rcl.excalibur.data.service.api.ShipTimeApi;
import com.rcl.excalibur.data.service.response.BaseResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

public final class ServiceUtil {

    private static final String SUCCESS = "SUCCESS";
    private static final String API_KEY_PARAM = "apikey";
    private static DiscoverApi discoverApi;
    private static GuestApi guestApi;
    private static SailDateApi sailDateApi;
    private static ShipTimeApi shipTimeApi;
    private static ShipStatsApi shipStatsApi;
    private static MenuApi menuApi;
    private static MockableApi mockableApi;


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

    public static ShipStatsApi getShipStatsApi() {
        if (shipStatsApi == null) {
            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(BuildConfig.SHIP_TIME_API_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    client(getClient())
                    .build();
            shipStatsApi = retrofit.create(ShipStatsApi.class);
        }
        return shipStatsApi;
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
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.MENUS_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
            menuApi = retrofit.create(MenuApi.class);
        }

        return menuApi;
    }

    public static SailDateApi getSailDateApi() {
        if (sailDateApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.SAIL_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
            sailDateApi = retrofit.create(SailDateApi.class);
        }
        return sailDateApi;
    }

    public static MockableApi getMockableApi() {
        if (mockableApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.MOCKABLE_IO_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
            mockableApi = retrofit.create(MockableApi.class);
        }
        return mockableApi;
    }

    private static OkHttpClient getClient() {
        final OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(BuildConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();
                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter(API_KEY_PARAM, BuildConfig.API_KEY)
                            .build();
                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                });

        if (BuildConfig.DEBUG) {
            //show request log in debug mode
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BODY));
        }

        return builder.build();
    }


}
