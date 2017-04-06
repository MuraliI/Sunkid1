package com.rcl.excalibur.internal.di.module.guest;

import android.content.Context;

import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.data.GuestPreferenceImpl;
import com.rcl.excalibur.data.mapper.guest.SecurityQuestionsResponseMapper;
import com.rcl.excalibur.data.service.GuestServicesImpl;
import com.rcl.excalibur.data.service.api.GuestApi;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.preference.GuestPreference;
import com.rcl.excalibur.domain.service.GuestServices;
import com.rcl.excalibur.internal.di.scopes.guest.GuestScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@GuestScope
@Module
public class GuestServicesModule {
    private Context context;

    public GuestServicesModule(Context context) {
        this.context = context;
    }


    @Provides
    SecurityQuestionsResponseMapper providesSecurityQuestionsResponseMapper() {
        return new SecurityQuestionsResponseMapper();
    }

    @Provides
    GuestApi providesGuestApi(OkHttpClient okHttpClient) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //show request log in debug mode
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        }

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BuildConfig.GUEST_API_URL).
                addConverterFactory(GsonConverterFactory.create()).
                client(okHttpClient)
                .build();
        return retrofit.create(GuestApi.class);
    }

    @Provides
    GuestServices providesGuestServices(GuestApi guestApi, SecurityQuestionsResponseMapper mapper,
                                        GetGuestPreferencesUseCase getGuestPreferencesUseCase) {
        return new GuestServicesImpl(guestApi, mapper, getGuestPreferencesUseCase);
    }

    @Provides
    GuestPreference providesGuestPreference() {
        return new GuestPreferenceImpl(context);
    }
}
