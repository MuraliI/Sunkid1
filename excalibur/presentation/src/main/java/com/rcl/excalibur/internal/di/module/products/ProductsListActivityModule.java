package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.internal.di.scopes.product.ProductsListActivityScope;
import com.rcl.excalibur.mvp.presenter.PlanListPresenter;
import com.rcl.excalibur.mvp.view.PlanListView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

import static com.rcl.excalibur.internal.di.module.products.ProductsListModule.POSITION;

@ProductsListActivityScope
@Module
public class ProductsListActivityModule {
    private BaseActivity baseActivity;

    public ProductsListActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    BaseActivity providesBaseActivity() {
        return baseActivity;
    }

    @Provides
    PlanListView providesPlanListView(BaseActivity baseActivity) {
        return new PlanListView(((PlanListActivity) baseActivity));
    }

    @Provides
    PlanListPresenter providesPlanListPresenter(PlanListView planListView, @Named(POSITION) int fragmentToShow) {
        return new PlanListPresenter(planListView, fragmentToShow);
    }
}
