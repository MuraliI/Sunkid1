package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.mapper.itinerary.ItineraryEventDataMapper;
import com.rcl.excalibur.data.service.response.itinerary.EventLocationResponse;
import com.rcl.excalibur.data.service.response.itinerary.EventTimeResponse;
import com.rcl.excalibur.data.service.response.itinerary.ItineraryEventResponse;
import com.rcl.excalibur.domain.ItineraryEvent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ItineraryEventDataMapperTest {

    ItineraryEventDataMapper itineraryEventDataMapper;
    ItineraryEventResponse entity1;
    ItineraryEventResponse entity2;

    @Before
    public void setUp() {
        itineraryEventDataMapper = new ItineraryEventDataMapper();
        entity1 = new ItineraryEventResponse();
        entity1.setEventID("26481901282912");
        entity1.setEventName("Tortola Dolphin Encounter");

        EventTimeResponse startTime = new EventTimeResponse();
        startTime.setEventDate("20170326");
        startTime.setEventTime("1600");
        startTime.setEventTimeZone("SHIP");

        EventTimeResponse endTime = new EventTimeResponse();
        endTime.setEventDate("20170326");
        endTime.setEventTime("1700");
        endTime.setEventTimeZone("SHIP");

        EventLocationResponse location = new EventLocationResponse();
        location.setLocationCode("STT");
        location.setLocationType("PORT");
        location.setLocationName("Charlotte Amalie, St. Thomas");

        entity1.setEventLocation(location);
        entity1.setEventStartTime(startTime);
        entity1.setEventEndTime(endTime);

        entity2 = null;

    }

    @Test
    public void transformToEntity() throws Exception {
        ItineraryEvent itineraryEvent = itineraryEventDataMapper.transform(entity1);
        assertNotNull(itineraryEvent);
        assertEquals(entity1.getEventID(), itineraryEvent.getId());
        assertEquals(entity1.getEventName(), itineraryEvent.getName());
        assertNotNull(entity1.getEventStartTime());
        assertNotNull(entity1.getEventEndTime());
        assertNotNull(entity1.getEventLocation());

        ItineraryEvent itineraryEventNull = itineraryEventDataMapper.transform(entity2);
        assertNull(itineraryEventNull);
    }

}
