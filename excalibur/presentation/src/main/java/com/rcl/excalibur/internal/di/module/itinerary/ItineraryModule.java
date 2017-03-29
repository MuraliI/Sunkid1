package com.rcl.excalibur.internal.di.module.itinerary;

import android.content.res.Resources;

import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.internal.di.scopes.itinerary.ItineraryScope;
import com.rcl.excalibur.model.itinerary.ItineraryProductModel;
import com.rcl.excalibur.model.itinerary.ItineraryProductModelMapper;

import dagger.Module;
import dagger.Provides;

@ItineraryScope
@Module
public class ItineraryModule {

    @Provides
    BaseDataMapper<ItineraryProductModel, ItineraryEvent> providesItineraryProductModelMapper(Resources resources) {
        return new ItineraryProductModelMapper(resources);
    }
}
