package com.rcl.excalibur.data.bean;

import java.util.List;

public class BaseResponseDto<T> {
    private int cruiseReservationID;
    private int guestID;
    private String sailingID;
    private String firstName;
    private String lastName;
    private List<T> itineraryEventGroups;

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

    public List<T> getItineraryEventGroups() {
        return itineraryEventGroups;
    }

    public void setItineraryEventGroups(List<T> itineraryEventGroups) {
        this.itineraryEventGroups = itineraryEventGroups;
    }
}
