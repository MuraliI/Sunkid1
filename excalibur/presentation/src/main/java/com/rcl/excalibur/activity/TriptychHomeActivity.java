package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.mapper.SubCategoryResponseDataMapper;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.data.repository.SubCategoriesDataRepository;
import com.rcl.excalibur.data.service.DiscoverServicesImpl;
import com.rcl.excalibur.data.service.SailDateServicesImpl;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateUseCase;
import com.rcl.excalibur.domain.interactor.GetSubCategoriesUseCase;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;
import com.rcl.excalibur.mvp.view.TriptychHomeView;

public class TriptychHomeActivity extends BaseActivity {
    protected TriptychHomePresenter presenter;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, TriptychHomeActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triptych_home_screen);

        DiscoverServicesImpl impl = new DiscoverServicesImpl(new ProductDataRepository());
        impl.setSubCategoryRepository(new SubCategoriesDataRepository());
        impl.setSubCategoryResponseDataMapper(new SubCategoryResponseDataMapper());

        presenter = new TriptychHomePresenter(new TriptychHomeView(this),
                new GetProductsUseCase(impl),
                new GetSubCategoriesUseCase(impl),
                new GetSaildDateUseCase(new SailDateServicesImpl(new SailDateDataRepository())));
        presenter.init();
    }

}
