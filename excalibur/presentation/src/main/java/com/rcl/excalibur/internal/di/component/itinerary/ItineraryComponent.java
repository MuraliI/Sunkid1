package com.rcl.excalibur.internal.di.component.itinerary;

import com.rcl.excalibur.internal.di.module.itinerary.ItineraryFragmentModule;
import com.rcl.excalibur.internal.di.module.itinerary.ItineraryModule;
import com.rcl.excalibur.internal.di.module.itinerary.ItineraryServicesModule;
import com.rcl.excalibur.internal.di.scopes.itinerary.ItineraryScope;

import dagger.Subcomponent;

@ItineraryScope
@Subcomponent(modules = {ItineraryServicesModule.class, ItineraryModule.class})
public interface ItineraryComponent {
    ItineraryFragmentComponent plus(ItineraryFragmentModule itineraryFragmentModule);
}
