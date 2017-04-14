package com.rcl.excalibur.domain.service;

import com.rcl.excalibur.domain.ItineraryEvent;

import java.util.List;

import io.reactivex.Observer;

public interface PlannerService {

    void myItinerary(Observer<List<ItineraryEvent>> observer);

}

