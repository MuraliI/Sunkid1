package com.rcl.excalibur.internal.di.component.itinerary;

import com.rcl.excalibur.fragments.ItineraryFragment;
import com.rcl.excalibur.internal.di.module.itinerary.ItineraryFragmentModule;
import com.rcl.excalibur.internal.di.scopes.itinerary.ItineraryFragmentScope;

import dagger.Subcomponent;

@ItineraryFragmentScope
@Subcomponent(modules = ItineraryFragmentModule.class)
public interface ItineraryFragmentComponent {
    void inject(ItineraryFragment fragment);
}
