package com.rcl.excalibur.data.utils;


import android.content.Context;

import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.data.service.api.DiscoveryApi;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ServiceUtils {

    private ServiceUtils() {
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

    public static Interceptor getInterceptor(final Context context, final String apiKey) {
        return chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
//                    .header("API_KEY", apiKey)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        };

    }
}
