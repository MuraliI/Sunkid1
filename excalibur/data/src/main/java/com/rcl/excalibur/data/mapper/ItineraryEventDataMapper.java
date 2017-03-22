package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.itinerary.ItineraryEventResponse;
import com.rcl.excalibur.domain.ItineraryEvent;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link ItineraryEventResponse} (in the data layer) to {@link ItineraryEvent} in the
 * domain layer.
 */
@Singleton
public class ItineraryEventDataMapper extends BaseDataMapper<ItineraryEvent, ItineraryEventResponse> {

    @Inject
    ItineraryEventDataMapper() {
    }

    @Override
    public ItineraryEvent transform(final ItineraryEventResponse eventResponse) {
        if (eventResponse == null) {
            return null;
        }
        final ItineraryEvent itineraryEvent = new ItineraryEvent();

        itineraryEvent.setId(eventResponse.getEventID());
        itineraryEvent.setName(eventResponse.getEventName());
        itineraryEvent.setStartDate(eventResponse.getEventStartTime().getDateObj());
        itineraryEvent.setEndDate(eventResponse.getEventEndTime().getDateObj());
        itineraryEvent.setLocation(eventResponse.getEventLocation().getLocationName());

        return itineraryEvent;
    }
}
