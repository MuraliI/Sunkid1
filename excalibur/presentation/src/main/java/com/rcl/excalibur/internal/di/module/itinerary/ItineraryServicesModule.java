package com.rcl.excalibur.internal.di.module.itinerary;

import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.data.mapper.itinerary.ItineraryEventDataMapper;
import com.rcl.excalibur.data.service.ItineraryServiceImpl;
import com.rcl.excalibur.data.service.response.itinerary.ItineraryEventResponse;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.internal.di.scopes.itinerary.ItineraryScope;

import dagger.Module;
import dagger.Provides;

@ItineraryScope
@Module
public class ItineraryServicesModule {

    @Provides
    BaseDataMapper<ItineraryEvent, ItineraryEventResponse> providesItineraryEventDataMapper() {
        return new ItineraryEventDataMapper();
    }

    @Provides
    ItineraryService providesItineraryService(BaseDataMapper<ItineraryEvent, ItineraryEventResponse> itineraryEventDataMapper) {
        return new ItineraryServiceImpl(itineraryEventDataMapper);
    }
}
