package com.rcl.excalibur.data.service.response.itinerary;

import java.util.List;

public class ResponseItinerary {
    private int cruiseReservationID;
    private int guestID;
    private String sailingID;
    private String firstName;
    private String lastName;
    private List<EventGroupResponse> itineraryEventGroups;

    public int getCruiseReservationID() {
        return cruiseReservationID;
    }

    public void setCruiseReservationID(int cruiseReservationID) {
        this.cruiseReservationID = cruiseReservationID;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public String getSailingID() {
        return sailingID;
    }

    public void setSailingID(String sailingID) {
        this.sailingID = sailingID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EventGroupResponse> getItineraryEventGroups() {
        return itineraryEventGroups;
    }

    public void setItineraryEventGroups(List<EventGroupResponse> itineraryEventGroups) {
        this.itineraryEventGroups = itineraryEventGroups;
    }
}
