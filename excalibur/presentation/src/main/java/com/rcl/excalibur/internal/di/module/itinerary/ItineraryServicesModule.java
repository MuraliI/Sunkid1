package com.rcl.excalibur.internal.di.module.itinerary;

import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.data.mapper.itinerary.ItineraryEventDataMapper;
import com.rcl.excalibur.data.service.ItineraryServiceImpl;
import com.rcl.excalibur.data.service.api.ItineraryApi;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.internal.di.scopes.itinerary.ItineraryScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@ItineraryScope
@Module
public class ItineraryServicesModule {

    @Provides
    ItineraryEventDataMapper providesItineraryEventDataMapper() {
        return new ItineraryEventDataMapper();
    }

    @Provides
    ItineraryApi providesItineraryApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BuildConfig.ITINERARY_API_URL).
                addConverterFactory(GsonConverterFactory.create()).
                client(okHttpClient)
                .build();
        return retrofit.create(ItineraryApi.class);
    }

    @Provides
    ItineraryService providesItineraryService(ItineraryEventDataMapper itineraryEventDataMapper, ItineraryApi itineraryApi) {
        return new ItineraryServiceImpl(itineraryEventDataMapper, itineraryApi);
    }
}
