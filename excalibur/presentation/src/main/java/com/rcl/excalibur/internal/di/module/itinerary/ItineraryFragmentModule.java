package com.rcl.excalibur.internal.di.module.itinerary;

import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.fragments.BaseFragment;
import com.rcl.excalibur.fragments.ItineraryFragment;
import com.rcl.excalibur.internal.di.scopes.itinerary.ItineraryFragmentScope;
import com.rcl.excalibur.model.itinerary.ItineraryProductModel;
import com.rcl.excalibur.mvp.presenter.itinerary.ItineraryPresenter;
import com.rcl.excalibur.mvp.view.itinerary.ItineraryView;

import dagger.Module;
import dagger.Provides;

@ItineraryFragmentScope
@Module
public class ItineraryFragmentModule {
    private BaseFragment fragment;

    public ItineraryFragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    BaseFragment providesBaseFragment() {
        return fragment;
    }

    @Provides
    ItineraryView providesItineraryView(BaseFragment fragment) {
        return new ItineraryView(((ItineraryFragment) fragment));
    }

    @Provides
    ItineraryPresenter providesItineraryPresenter(ItineraryView itineraryView,
                                                  ItineraryService itineraryService,
                                                  BaseDataMapper<ItineraryProductModel, ItineraryEvent> dataMapper) {
        return new ItineraryPresenter(itineraryView, itineraryService, dataMapper);
    }
}
