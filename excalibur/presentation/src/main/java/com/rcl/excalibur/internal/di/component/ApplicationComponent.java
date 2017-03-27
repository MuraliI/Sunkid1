package com.rcl.excalibur.internal.di.component;

import android.content.Context;

import com.rcl.excalibur.domain.repository.CategoryRepository;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;
import com.rcl.excalibur.domain.repository.ProductRepository;
import com.rcl.excalibur.domain.service.DiscoverService;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.internal.di.module.ApplicationModule;
import com.rcl.excalibur.mvp.presenter.DiscoverItemDetailPresenter;
import com.rcl.excalibur.mvp.presenter.DiscoverItemListPresenter;
import com.rcl.excalibur.mvp.presenter.DiscoverTabPresenter;
import com.rcl.excalibur.mvp.presenter.HomePresenter;
import com.rcl.excalibur.mvp.presenter.itinerary.ItineraryPresenter;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(DiscoverItemListPresenter presenter);

    void inject(DiscoverItemDetailPresenter presenter);

    void inject(HomePresenter presenter);

    void inject(TriptychHomePresenter presenter);

    void inject(DiscoverTabPresenter presenter);

    void inject(ItineraryPresenter presenter);

    //Exposed to sub-graphs.
    Context context();

    DiscoverItemRepository discoverItemRepository();

    CategoryRepository categoryRepository();

    DiscoverService discoverService();

    ItineraryService itineraryService();

    ProductRepository productRepository();
}
